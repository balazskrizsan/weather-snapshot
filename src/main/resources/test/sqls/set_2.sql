insert into sites (id, name, domain)
values (1, 'D1', 'http://sql2-1.hu'),
       (2, 'D2', 'http://sql2-2.hu');

insert into site_uris (id, site_id, site_uri_id, latest_parser_version_id, method, uri, params)
values (1, 1, 1, 1, 1, '/aaa', null),
       (2, 1, 2, 1, 1, '/bbb', null),
       (3, 2, 1, 2, 2, '/ccc', '{
         "a1": "1",
         "a2": "2"
       }'),
       (4, 2, 2, 2, 2, '/ddd', '{
         "b1": "a",
         "b2": "b"
       }');
