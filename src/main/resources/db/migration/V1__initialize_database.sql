create table if not exists device
(
    id            bigserial
        constraint pk__device__id primary key,
    serial_number varchar(11) not null
        constraint u__device__serial_number unique,
    brand         varchar(150) not null,
    model         varchar(200) not null,
    created_at    timestamp(0) not null,
    updated_at    timestamp(0),
    deleted_at    timestamp(0)
);

create table if not exists warranty
(
    id              bigserial
        constraint pk__warranty__id primary key,
    device_id       bigint
        constraint fk__warranty__device__id references device (id),
    purchase_date   date         not null,
    warranty_status varchar(20)  not null
        constraint c__warranty_status__status check (warranty_status in ('ACTIVE', 'EXPIRED', 'PENDING')),
    created_at      timestamp(0) not null,
    updated_at      timestamp(0)
);

insert into device (serial_number, brand, model, created_at)
values ('12349012344', 'brand', 'phone', current_timestamp),
       ('12789012361', 'brand', 'tab', current_timestamp);

insert into warranty (device_id, purchase_date, warranty_status, created_at)
values (1, '2022-07-01', 'ACTIVE', current_timestamp),
       (2, '2021-06-15', 'EXPIRED', current_timestamp);
