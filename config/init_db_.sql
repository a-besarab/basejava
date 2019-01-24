create table resume
(
  uuid      char(36) not null
    constraint resume_pk
      primary key,
  full_name text
);

create table contact
(
  id          serial   not null
    constraint contact_pk
      primary key,
  type        text     not null,
  value       text     not null,
  resume_uuid char(36) not null
    constraint contact_resume_uuid_fk
      references resume
      on update restrict on delete cascade
);

create table if not exists section
(
  id          serial   not null
    constraint section_pk
      primary key,
  type        text     not null,
  value       text     not null,
  resume_uuid char(36) not null
    constraint section_resume_uuid_fk
      references resume
      on update restrict on delete cascade
);

create unique index section_uuid_type_index
  on section (resume_uuid, type);

create unique index contact_uuid_type_index
  on contact (resume_uuid, type);
