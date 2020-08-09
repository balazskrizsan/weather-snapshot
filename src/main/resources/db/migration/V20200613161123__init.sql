create table sites (
    id     serial       not null,
    name   varchar (30) not null,
    domain varchar (50) not null,
    primary key (id)
);
insert into sites (name, domain) values
    ('Időkép', 'http://idokep.hu'),
    ('Met', 'https://met.hu');

create table site_uris (
    id                       serial        not null,
    site_id                  int           not null,
    site_uri_id              int           not null,
    latest_parser_version_id int           not null,
    method                   int           not null, /* 1:GET, 2: POST*/
    uri                      varchar (255) not null,
    params                   json,
    primary                  key (id)
);
insert into site_uris (site_id, site_uri_id, latest_parser_version_id, method, uri, params) values
    (1, 1, 1, 1, '/elorejelzes/Budapest', null),
    (1, 1, 1, 1, '/elorejelzes/Debrecen', null),
    (1, 1, 1, 1, '/elorejelzes/Győr', null),
    (1, 1, 1, 1, '/elorejelzes/Szeged', null),
    (1, 1, 1, 1, '/elorejelzes/Pécs', null),
    (1, 1, 1, 1, '/elorejelzes/Miskolc', null),
    (1, 1, 1, 1, '/elorejelzes/Zamárdi', null),
    (1, 2, 1, 1, '/30napos/Budapest', null),
    (1, 2, 1, 1, '/30napos/Debrecen', null),
    (1, 2, 1, 1, '/30napos/Győr', null),
    (1, 2, 1, 1, '/30napos/Szeged', null),
    (1, 2, 1, 1, '/30napos/Pécs', null),
    (1, 2, 1, 1, '/30napos/Miskolc', null),
    (1, 2, 1, 1, '/30napos/Zamárdi', null),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "46.20", "n": "20.15", "tel": "Szeged"}'),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "46.00", "n": "18.35", "tel": "Pécs"}'),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "47.60", "n": "17.75", "tel": "Győr"}'),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "47.50", "n": "21.65", "tel": "Debrecen"}'),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "47.50", "n": "19.10", "tel": "Budapest"}'),
    (2, 3, 1, 2, '/idojaras/elorejelzes/magyarorszagi_telepulesek/main.php', '{"valtozatlan": "true", "kod": "0", "lt": "48.00", "n": "20.75", "tel": "Miskolc"}');

create table html_logs(
    id                bigserial not null,
    site_id           int       not null,
    site_uri_id       int       not null,
    parser_version_id int       not null,
    html              text      not null,
    created_at        timestamp not null,
    parsed            timestamp null default NULL,
    primary           key(id)
);

create table parsed_temperature_snapshots (
   id               bigserial not null,
   html_logs_id     bigint    not null,
   temperature_time timestamp not null,
   temperature_min  int       not null,
   temperature_max  int       not null,
   primary          key (id)
);
