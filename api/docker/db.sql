--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4 (Debian 14.4-1.pgdg110+1)
-- Dumped by pg_dump version 14.4 (Debian 14.4-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: carts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carts (
    id integer NOT NULL,
    created_date timestamp without time zone,
    quantity integer NOT NULL,
    product_id integer,
    user_id integer NOT NULL
);


ALTER TABLE public.carts OWNER TO postgres;

--
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.carts ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.carts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id integer NOT NULL,
    category_name character varying(255),
    description character varying(255)
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.categories ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: orderitems; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderitems (
    id integer NOT NULL,
    created_date timestamp without time zone,
    price double precision,
    quantity integer,
    order_id integer,
    product_id integer
);


ALTER TABLE public.orderitems OWNER TO postgres;

--
-- Name: orderitems_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orderitems ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orderitems_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    created_date timestamp without time zone,
    session_id character varying(255),
    total_price double precision,
    user_id integer
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    brand character varying(255),
    description character varying(1023),
    image_url character varying(255),
    name character varying(255),
    price double precision NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.products ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: wishlists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlists (
    id integer NOT NULL,
    created_date timestamp without time zone,
    product_id integer,
    user_id integer NOT NULL
);


ALTER TABLE public.wishlists OWNER TO postgres;

--
-- Name: wishlists_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.wishlists ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.wishlists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.carts (id, created_date, quantity, product_id, user_id) FROM stdin;
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, category_name, description) FROM stdin;
10	New collection	new collection
11	Men	men
12	Women	women
\.


--
-- Data for Name: orderitems; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderitems (id, created_date, price, quantity, order_id, product_id) FROM stdin;
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, created_date, session_id, total_price, user_id) FROM stdin;
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, brand, description, image_url, name, price, category_id) FROM stdin;
28	Nike	Originally introduced in 1972 as a basketball shoe, the Blazer has become a stylish staple for skateboard and sneaker fans. First a simple high-top canvas model, then a mid-top leather shoe, and finally a relaxed low cut, this shoe continues to improve over time.	https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,b_rgb:f5f5f5/c75cb20c-a6af-4cd7-8356-dccf113b93fc/chaussure-blazer-mid-77-prm-pour-SPk0kf.png	Nike Blazer Mid "77 PRM	109.99	10
29	Nike	The charm continues with the Nike Air Force 1 “07. This iconic basketball silhouette breathes new life into its most notable elements: impeccable leather, bright colors and just the right amount of sparkle to make you shine.	https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,b_rgb:f5f5f5/350e7f3a-979a-402b-9396-a8a998dd76ab/chaussure-air-force-1-07-pour-xCxt0q.png	Nike Air Force 1 "07	119.99	11
30	Nike	The Nike Air Force 1 Shadow brings a playful twist to a classic basketball model. The DNA of the AF1 is highlighted by the layered aesthetic, double logo and oversized midsole of this bold new model.	https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,b_rgb:f5f5f5/d223ea7d-84a2-44ac-b89a-b6e9b71ed575/chaussure-air-force-1-shadow-pour-X47QLb.png	Nike Air Force 1 Shadow	129.99	12
31	Yeezy	ONYX.	https://assets.adidas.com/images/w_840,h_840,q_auto:sensitive/2c105d93228c43bcab3fac5c01653cd5_9366/FZ1270_01_standard.jpg	YEEZY BOOST 380	230	10
32	Balenciaga	Triple S Trainers Allover Logo in light blue and white technical material.	https://balenciaga.dam.kering.com/m/75177882a15fdfc2/Large-524039W2FA14090_F.jpg?v=2	TRIPLE S TRAINERS ALLOVER LOGO	1090	12
33	Balenciaga	Speed Recycled Knit Trainers in black knit, white and black sole unit.	https://balenciaga.dam.kering.com/m/2797f8ceaa02b43a/eCom-645056W2DBQ1015_F.jpg?v=2	SPEED RECYCLED KNIT TRAINERS	645	11
34	New Balance	The 2002R reintroduces a favorite running model from 2010, featuring an original design and better performance benefits. Do not call it retro though. The premium design for the suede and mesh upper features sleek curves and cut-out segments that give it a futuristic look on a classic base. The performance of the shock-absorbing outsole equipped with Stability Web and N-ergy, the ABZORB midsole and the ABZORB SBS heel cushioning provide comfort and results that cannot be traced back to appearance.	https://nb.scene7.com/is/image/NB/ml2002re_nb_02_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600	2002R	130	10
35	New Balance	The most New Balance shoe ever, that is self-explanatory, right? Not quite. The 574 might be our most unlikely icon. The 574 was designed to be a reliable shoe capable of doing many different things rather than being a platform for revolutionary technologies or a showcase for premium materials. This modest, unpretentious versatility is exactly what has propelled the 574 into one of the best shoes of all time. A hybrid road/trail model with a wider shape than the narrow models of the previous generation, the 574 offered a unique blend of novelties, differences, simplicity, robustness, durability and comfort that made it essential in any the planet. This is why today, the 574 embodies the New Balance style that defies boundaries and is worn by everyone.	https://nb.scene7.com/is/image/NB/ml574hs2_nb_02_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600	574	110	11
36	New balance	Reminiscent of a sneaker fan style, with a trend towards chunky shoes inspired by the 90s, the 452 for women is both retro and “super dad” but with classic comfort and support. Bold layers are working overtime to ride this trendy excessive style.	https://nb.scene7.com/is/image/NB/wx452sg_nb_02_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600	452	90	12
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, password, role, username) FROM stdin;
1	admin@mail.com	$2a$10$sArGbGuzNR9ay1xwdhUZvuDSd8FxKezB/r.JJuriKGUpY6bfpG042	ROLE_ADMIN	admin
2	user@mail.com	$2a$10$VAc.IrAfuXLD8zM7qGOegeuY8IN.i1VnRF5O6PxBDMb9jy6v7ra7q	ROLE_USER	user
\.


--
-- Data for Name: wishlists; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlists (id, created_date, product_id, user_id) FROM stdin;
\.


--
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carts_id_seq', 1, false);


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 12, true);


--
-- Name: orderitems_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderitems_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 36, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- Name: wishlists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wishlists_id_seq', 1, false);


--
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (id);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: orderitems orderitems_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT orderitems_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: wishlists wishlists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlists
    ADD CONSTRAINT wishlists_pkey PRIMARY KEY (id);


--
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: wishlists fk330pyw2el06fn5g28ypyljt16; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlists
    ADD CONSTRAINT fk330pyw2el06fn5g28ypyljt16 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: carts fkb5o626f86h46m4s7ms6ginnop; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fkb5o626f86h46m4s7ms6ginnop FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: wishlists fkl7ao98u2bm8nijc1rv4jobcrx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlists
    ADD CONSTRAINT fkl7ao98u2bm8nijc1rv4jobcrx FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: orderitems fkm3mp87f5ygbbfuqfdhc09y9a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT fkm3mp87f5ygbbfuqfdhc09y9a FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- Name: carts fkmd2ap4oxo3wvgkf4fnaye532i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fkmd2ap4oxo3wvgkf4fnaye532i FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: products fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- Name: orderitems fkqu7dfdpbhltsgxfq2ahkdnyv5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderitems
    ADD CONSTRAINT fkqu7dfdpbhltsgxfq2ahkdnyv5 FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- PostgreSQL database dump complete
--
