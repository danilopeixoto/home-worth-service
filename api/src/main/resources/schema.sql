create extension if not exists "uuid-ossp";

create table if not exists district (
  id uuid default uuid_generate_v4(),
  name text not null,
  square_meter_price double precision not null,
  constraint id_pk_constraint primary key (id),
  constraint name_unique_constraint unique (name)
);

create index if not exists district_name_index on district (name);
