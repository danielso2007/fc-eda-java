INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), 'b1b2d7d8-6f44-4a9e-bcd5-9b8a4a30fa04', 'ana.silva@example.com', 'Ana Silva');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '0f7a3c71-9236-4cf0-b476-0ab92d7cfba0', 'carlos.souza@example.com', 'Carlos Souza');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '4c53747e-03a2-4351-8f8c-795dbdc3e7ea', 'julia.martins@example.com', 'Júlia Martins');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '6f9329ba-3817-49d2-a981-c8fb7bb4d427', 'rafael.lima@example.com', 'Rafael Lima');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), 'fa37f13d-7afc-47d4-b9f9-3211bb4cc609', 'mariana.oliveira@example.com', 'Mariana Oliveira');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '9f9e0c20-8931-4a65-9c1e-c811d1b677b7', 'pedro.almeida@example.com', 'Pedro Almeida');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), 'e26f2e0e-6e60-4cd1-9ae6-7ef6defd6058', 'larissa.santos@example.com', 'Larissa Santos');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '2d5a7342-5775-4a53-a27f-16c1a4a5054e', 'lucas.rocha@example.com', 'Lucas Rocha');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '8e6e1e8d-bc74-48af-bf4c-367389981f27', 'fernanda.castro@example.com', 'Fernanda Castro');
INSERT INTO public.clients(created_at, updated_at, id, email, name) VALUES (now(), now(), '5cae76c0-bd5e-4a3c-9d2d-23ced69fb55c', 'gustavo.melo@example.com', 'Gustavo Melo');

INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), 'b1b2d7d8-6f44-4a9e-bcd5-9b8a4a30fa04', 'c1d2e3f4-1111-aaaa-bbbb-000000000001');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (849.25, now(), now(), '0f7a3c71-9236-4cf0-b476-0ab92d7cfba0', 'c1d2e3f4-2222-cccc-dddd-000000000002');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1150.75, now(), now(), '4c53747e-03a2-4351-8f8c-795dbdc3e7ea', 'c1d2e3f4-3333-eeee-ffff-000000000003');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), '6f9329ba-3817-49d2-a981-c8fb7bb4d427', 'c1d2e3f4-4444-1111-2222-000000000004');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), 'fa37f13d-7afc-47d4-b9f9-3211bb4cc609', 'c1d2e3f4-5555-3333-4444-000000000005');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), '9f9e0c20-8931-4a65-9c1e-c811d1b677b7', 'c1d2e3f4-6666-5555-6666-000000000006');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (500.00, now(), now(), 'e26f2e0e-6e60-4cd1-9ae6-7ef6defd6058', 'c1d2e3f4-7777-7777-8888-000000000007');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1500.00, now(), now(), '2d5a7342-5775-4a53-a27f-16c1a4a5054e', 'c1d2e3f4-8888-9999-0000-000000000008');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), '8e6e1e8d-bc74-48af-bf4c-367389981f27', 'c1d2e3f4-9999-aaaa-bbbb-000000000009');
INSERT INTO public.accounts(balance, created_at, updated_at, client_id, id) VALUES (1000.00, now(), now(), '5cae76c0-bd5e-4a3c-9d2d-23ced69fb55c', 'c1d2e3f4-0000-cccc-dddd-000000000010');

INSERT INTO public.transactions(amount, created_at, account_from_id, account_to_id, id) VALUES(150.75, now(), 'c1d2e3f4-2222-cccc-dddd-000000000002'::uuid, 'c1d2e3f4-3333-eeee-ffff-000000000003'::uuid, 'a0553acd-8817-44e5-9393-6cdd9a2eee84'::uuid);
INSERT INTO public.transactions(amount, created_at, account_from_id, account_to_id, id) VALUES(500.0, now(), 'c1d2e3f4-7777-7777-8888-000000000007'::uuid, 'c1d2e3f4-8888-9999-0000-000000000008'::uuid, '270754b1-75f0-474b-b248-b41e1f131f50'::uuid);
INSERT INTO public.transactions(amount, created_at, account_from_id, account_to_id, id) VALUES(258.47, now(), 'c1d2e3f4-9999-aaaa-bbbb-000000000009'::uuid, 'c1d2e3f4-0000-cccc-dddd-000000000010'::uuid, 'ac310dc1-5eca-4ddf-bba9-5ba2e09a464f'::uuid);
INSERT INTO public.transactions(amount, created_at, account_from_id, account_to_id, id) VALUES(500.0, now(), 'c1d2e3f4-6666-5555-6666-000000000006'::uuid, 'c1d2e3f4-9999-aaaa-bbbb-000000000009'::uuid, '405655a6-bf7a-4ce3-9095-44d8560eaed7'::uuid);