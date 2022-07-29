--
-- PostgreSQL database dump
--

-- Dumped from database version 13.7 (Debian 13.7-1.pgdg110+1)
-- Dumped by pg_dump version 13.7 (Debian 13.7-1.pgdg110+1)

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

--
-- Name: core; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA core;


ALTER SCHEMA core OWNER TO postgres;

--
-- Name: operations; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA operations;


ALTER SCHEMA operations OWNER TO postgres;

--
-- Name: utilitarian; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA utilitarian;


ALTER SCHEMA utilitarian OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: rol; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.rol (
    id_rol integer NOT NULL,
    name character varying(200),
    description character varying(1000),
    status integer DEFAULT 1
);


ALTER TABLE core.rol OWNER TO postgres;

--
-- Name: rol_id_rol_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE core.rol_id_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE core.rol_id_rol_seq OWNER TO postgres;

--
-- Name: rol_id_rol_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE core.rol_id_rol_seq OWNED BY core.rol.id_rol;


--
-- Name: user; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core."user" (
    id_user integer NOT NULL,
    full_name character varying(2000),
    email character varying(2000),
    pass text,
    id_country integer,
    status integer DEFAULT 1,
    date_reg timestamp without time zone DEFAULT now(),
    date_modified timestamp without time zone DEFAULT now(),
    date_birth date,
    gender character varying(15),
    address text,
    phone_number character varying(50),
    id_language integer,
    id_education integer,
    date_of_birth date
);


ALTER TABLE core."user" OWNER TO postgres;

--
-- Name: user_id_user_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE core.user_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE core.user_id_user_seq OWNER TO postgres;

--
-- Name: user_id_user_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE core.user_id_user_seq OWNED BY core."user".id_user;


--
-- Name: user_rol; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE core.user_rol (
    id_user_rol integer NOT NULL,
    id_user integer,
    id_rol integer
);


ALTER TABLE core.user_rol OWNER TO postgres;

--
-- Name: user_rol_id_user_rol_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE core.user_rol_id_user_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE core.user_rol_id_user_rol_seq OWNER TO postgres;

--
-- Name: user_rol_id_user_rol_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE core.user_rol_id_user_rol_seq OWNED BY core.user_rol.id_user_rol;


--
-- Name: courses; Type: TABLE; Schema: operations; Owner: postgres
--

CREATE TABLE operations.courses (
    id_course integer NOT NULL,
    name character varying(5000),
    description text,
    img text,
    id_user_teacher integer,
    duration_time character varying(50),
    price double precision,
    date_init date,
    date_end date,
    call_number character varying(200),
    status integer DEFAULT 1
);


ALTER TABLE operations.courses OWNER TO postgres;

--
-- Name: courses_id_course_seq; Type: SEQUENCE; Schema: operations; Owner: postgres
--

CREATE SEQUENCE operations.courses_id_course_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE operations.courses_id_course_seq OWNER TO postgres;

--
-- Name: courses_id_course_seq; Type: SEQUENCE OWNED BY; Schema: operations; Owner: postgres
--

ALTER SEQUENCE operations.courses_id_course_seq OWNED BY operations.courses.id_course;


--
-- Name: enrolled_courses; Type: TABLE; Schema: operations; Owner: postgres
--

CREATE TABLE operations.enrolled_courses (
    id_enrolled_courses integer NOT NULL,
    id_course integer,
    status integer DEFAULT 1,
    id_user integer,
    date_reg timestamp without time zone DEFAULT now(),
    id_payment_plan integer
);


ALTER TABLE operations.enrolled_courses OWNER TO postgres;

--
-- Name: enrolled_courses_id_enrolled_courses_seq; Type: SEQUENCE; Schema: operations; Owner: postgres
--

CREATE SEQUENCE operations.enrolled_courses_id_enrolled_courses_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE operations.enrolled_courses_id_enrolled_courses_seq OWNER TO postgres;

--
-- Name: enrolled_courses_id_enrolled_courses_seq; Type: SEQUENCE OWNED BY; Schema: operations; Owner: postgres
--

ALTER SEQUENCE operations.enrolled_courses_id_enrolled_courses_seq OWNED BY operations.enrolled_courses.id_enrolled_courses;


--
-- Name: payment_plan; Type: TABLE; Schema: operations; Owner: postgres
--

CREATE TABLE operations.payment_plan (
    id_payment_plan integer NOT NULL,
    number_installments integer,
    interest double precision,
    status integer DEFAULT 1,
    discount double precision,
    name_plan character varying(500)
);


ALTER TABLE operations.payment_plan OWNER TO postgres;

--
-- Name: payment_plan_id_payment_plan_seq; Type: SEQUENCE; Schema: operations; Owner: postgres
--

CREATE SEQUENCE operations.payment_plan_id_payment_plan_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE operations.payment_plan_id_payment_plan_seq OWNER TO postgres;

--
-- Name: payment_plan_id_payment_plan_seq; Type: SEQUENCE OWNED BY; Schema: operations; Owner: postgres
--

ALTER SEQUENCE operations.payment_plan_id_payment_plan_seq OWNED BY operations.payment_plan.id_payment_plan;


--
-- Name: country; Type: TABLE; Schema: utilitarian; Owner: postgres
--

CREATE TABLE utilitarian.country (
    id_country integer NOT NULL,
    name character varying(500),
    status integer DEFAULT 1,
    date_reg timestamp without time zone DEFAULT now()
);


ALTER TABLE utilitarian.country OWNER TO postgres;

--
-- Name: country_id_country_seq; Type: SEQUENCE; Schema: utilitarian; Owner: postgres
--

CREATE SEQUENCE utilitarian.country_id_country_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilitarian.country_id_country_seq OWNER TO postgres;

--
-- Name: country_id_country_seq; Type: SEQUENCE OWNED BY; Schema: utilitarian; Owner: postgres
--

ALTER SEQUENCE utilitarian.country_id_country_seq OWNED BY utilitarian.country.id_country;


--
-- Name: education_level; Type: TABLE; Schema: utilitarian; Owner: postgres
--

CREATE TABLE utilitarian.education_level (
    id_education_level integer NOT NULL,
    name character varying(500),
    description character varying(5000),
    status integer DEFAULT 1
);


ALTER TABLE utilitarian.education_level OWNER TO postgres;

--
-- Name: edutation_level_id_education_level_seq; Type: SEQUENCE; Schema: utilitarian; Owner: postgres
--

CREATE SEQUENCE utilitarian.edutation_level_id_education_level_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilitarian.edutation_level_id_education_level_seq OWNER TO postgres;

--
-- Name: edutation_level_id_education_level_seq; Type: SEQUENCE OWNED BY; Schema: utilitarian; Owner: postgres
--

ALTER SEQUENCE utilitarian.edutation_level_id_education_level_seq OWNED BY utilitarian.education_level.id_education_level;


--
-- Name: language; Type: TABLE; Schema: utilitarian; Owner: postgres
--

CREATE TABLE utilitarian.language (
    id_language integer NOT NULL,
    name character varying(500),
    status integer DEFAULT 1,
    date_reg timestamp without time zone DEFAULT now()
);


ALTER TABLE utilitarian.language OWNER TO postgres;

--
-- Name: language_id_languaje_seq; Type: SEQUENCE; Schema: utilitarian; Owner: postgres
--

CREATE SEQUENCE utilitarian.language_id_languaje_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilitarian.language_id_languaje_seq OWNER TO postgres;

--
-- Name: language_id_languaje_seq; Type: SEQUENCE OWNED BY; Schema: utilitarian; Owner: postgres
--

ALTER SEQUENCE utilitarian.language_id_languaje_seq OWNED BY utilitarian.language.id_language;


--
-- Name: rol id_rol; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.rol ALTER COLUMN id_rol SET DEFAULT nextval('core.rol_id_rol_seq'::regclass);


--
-- Name: user id_user; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core."user" ALTER COLUMN id_user SET DEFAULT nextval('core.user_id_user_seq'::regclass);


--
-- Name: user_rol id_user_rol; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.user_rol ALTER COLUMN id_user_rol SET DEFAULT nextval('core.user_rol_id_user_rol_seq'::regclass);


--
-- Name: courses id_course; Type: DEFAULT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.courses ALTER COLUMN id_course SET DEFAULT nextval('operations.courses_id_course_seq'::regclass);


--
-- Name: enrolled_courses id_enrolled_courses; Type: DEFAULT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.enrolled_courses ALTER COLUMN id_enrolled_courses SET DEFAULT nextval('operations.enrolled_courses_id_enrolled_courses_seq'::regclass);


--
-- Name: payment_plan id_payment_plan; Type: DEFAULT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.payment_plan ALTER COLUMN id_payment_plan SET DEFAULT nextval('operations.payment_plan_id_payment_plan_seq'::regclass);


--
-- Name: country id_country; Type: DEFAULT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.country ALTER COLUMN id_country SET DEFAULT nextval('utilitarian.country_id_country_seq'::regclass);


--
-- Name: education_level id_education_level; Type: DEFAULT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.education_level ALTER COLUMN id_education_level SET DEFAULT nextval('utilitarian.edutation_level_id_education_level_seq'::regclass);


--
-- Name: language id_language; Type: DEFAULT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.language ALTER COLUMN id_language SET DEFAULT nextval('utilitarian.language_id_languaje_seq'::regclass);


--
-- Data for Name: rol; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY core.rol (id_rol, name, description, status) FROM stdin;
1	ADMIN	Administrator sistem	1
2	TEACHER	Teacher of course	1
3	USER	student or registered visitor 	1
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY core."user" (id_user, full_name, email, pass, id_country, status, date_reg, date_modified, date_birth, gender, address, phone_number, id_language, id_education, date_of_birth) FROM stdin;
2	Sara Gallardo	sara@gmail.com	12345	1	1	2022-07-24 23:28:01.624895	2022-07-24 23:28:01.624895	\N	\N	\N	\N	\N	\N	\N
1	Edson	edson.sosa@gmail.com	$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq	\N	1	2022-07-21 06:58:23.698062	2022-07-21 06:58:23.698062	\N	M	Pampahasi	67128003	1	2	\N
3	Grover Sosa	\N	12345	1	1	2022-07-25 19:21:17.439721	2022-07-25 19:21:17.439721	\N	\N	\N	\N	\N	\N	\N
6	dsfdsf	strisdfdsng	dsffs	4	1	2022-07-25 23:10:01.179648	2022-07-25 23:10:01.179648	\N	\N	\N	\N	\N	\N	\N
14	Juan perez	juan@gmail.com	12345	2	1	2022-07-26 23:38:52.659656	2022-07-26 23:38:52.659656	\N	\N	\N	\N	\N	\N	\N
15	Junito mamani	juanito@gmail.com	$2a$10$aVJjESN3hk7vrGz8FYJcGepANAUrzFPD.fjYxvVcRuptkzB9AtIJO	2	1	2022-07-26 23:48:56.89465	2022-07-26 23:48:56.89465	\N	\N	\N	\N	\N	\N	\N
20	Nora Gallardo	edson.vladimir@gmail.com	$2a$10$BGifxAXZjL4zLJo9IzLLtORknwHduXI3FuiPcuzKvTeVJ1EEPHRPu	1	1	2022-07-27 22:38:52.707015	2022-07-27 22:38:52.707015	\N	\N	\N	\N	\N	\N	\N
28	Freddy Sosa Gallardo	freddy@gmail.com	$2a$10$8dGJAMC3VldvdrtUq/nsEukrGsB0U9bAdoQ5zhfpz7OobotSCisHm	2	1	2022-07-27 23:23:10.366164	2022-07-27 23:23:10.366164	\N	\N	\N	\N	\N	\N	\N
29	pepito peres	pepi@gmail.com	$2a$10$NMihaGRiG7n9Q1YhV/EbtOmw6X.7uGfctHm418dAx1DA2bFt/C1m.	2	1	2022-07-28 20:57:02.906756	2022-07-28 20:57:02.906756	\N	\N	\N	\N	\N	\N	\N
33	Yusel Calle	yusel@gmail.com	$2a$10$piMuiV9aw2jeF3B9dXBSou3WE9R3FGxbRQwrcLM2w/gX4xLVqCi6y	5	1	2022-07-28 23:37:10.528118	2022-07-28 23:37:10.528118	\N	\N	\N	\N	\N	\N	\N
34	sadasdsad	dsadasdasd@gmail.com	$2a$10$fdzdQvt10dVME22xfM3Vy.yvMBim8jfdNKh/Z8EDxB7Lb.FfVg/ji	4	1	2022-07-28 23:38:29.296761	2022-07-28 23:38:29.296761	\N	\N	\N	\N	\N	\N	\N
35	sadsadsadas	asdasdasdas@gmail.com	$2a$10$o5HVS6jfGeveQIozPMvP7OmO3tPBMTg/AI7RAf5HtUHaDrrzYHDai	2	1	2022-07-28 23:39:05.05495	2022-07-28 23:39:05.05495	\N	\N	\N	\N	\N	\N	\N
36	dsadasdas	asdas@gmail.com	$2a$10$258wzvx0e2Nqe3x3qvkUkeEHr60CLc5F/zYy1OxLX0O9yn6CFhokm	5	1	2022-07-28 23:41:19.830755	2022-07-28 23:41:19.830755	\N	\N	\N	\N	\N	\N	\N
37	sadasdas	sadas@gmail.com	$2a$10$zjZist0qQ.HCPRFQLk7S6uD2.W5W1p9c5/8QaFS0erfvOK3K68Ysi	5	1	2022-07-28 23:41:42.328449	2022-07-28 23:41:42.328449	\N	\N	\N	\N	\N	\N	\N
38	sadsa	asdas@gmail.com	$2a$10$0wBC/jzRFgQVZE3GO23KbueJ6JD4pMaGvz/VdI8dyZ8XL8vTEMEBG	3	1	2022-07-28 23:42:19.856129	2022-07-28 23:42:19.856129	\N	\N	\N	\N	\N	\N	\N
39	sadasd	sadsa@gmail.com	$2a$10$zi3uhlPXcW8mKNbE/KEIh.fTxiMie5B3DtGjSjEQIeV1Le/k0z8v2	4	1	2022-07-28 23:44:31.728762	2022-07-28 23:44:31.728762	\N	\N	\N	\N	\N	\N	\N
40	ewqewqewq	dsasdas@gamsada.com	$2a$10$DJG7VY0v6p2v2Skdi67mgegs57sU1JvP6nB77tcD4JXwHg1YTUZYO	4	1	2022-07-29 00:01:51.656615	2022-07-29 00:01:51.656615	\N	\N	\N	\N	\N	\N	\N
42	ewqewqewq	dsasdas@gamsada.com	$2a$10$1rtWDpbImYHfgos18xZFIeyLVp2k8iUf7pX9aE.n9E3XJM8Ot3Xn.	3	1	2022-07-29 00:02:06.266342	2022-07-29 00:02:06.266342	\N	\N	\N	\N	\N	\N	\N
44	ewqewqewq	dsasdas@gamsada.com	$2a$10$6QublDV24MuGMDDUdFxHHeF7z864XPybMMxI2mThPzoIoWmHJtoVu	4	1	2022-07-29 00:02:19.367758	2022-07-29 00:02:19.367758	\N	\N	\N	\N	\N	\N	\N
46	ewqewqewq	dsasdas@gamsada.com	$2a$10$Z5pzpPARp54F5ellaBb1.OctgLNduPH6nHaZg6GsIXAwe/r6fgsf2	5	1	2022-07-29 00:02:51.801389	2022-07-29 00:02:51.801389	\N	\N	\N	\N	\N	\N	\N
47	ewqewqewq	dsasdas@gamsada.com	$2a$10$01/I/mhS6K682ypOWV.JCOaeanrpNNkzHD3EqDTrdGksT0PWjMY3a	5	1	2022-07-29 00:03:09.444399	2022-07-29 00:03:09.444399	\N	\N	\N	\N	\N	\N	\N
49	Nortiton	norton@gmail.com	$2a$10$fRpl3MdAVrnD1Zj2t3RSeOb/ysZWVJdt6A6XDqjdQuRbz0w9rmb2i	4	1	2022-07-29 00:08:12.440498	2022-07-29 00:08:12.440498	\N	\N	\N	\N	\N	\N	\N
50	vladimir sosa	vladi@gmail.com	$2a$10$c0KEk2L6IMnM6cEE49C6oOUi1bWxMlOnX8o.T8bgfm1JVhhdEPIQ6	4	1	2022-07-29 00:08:57.771569	2022-07-29 00:08:57.771569	\N	\N	\N	\N	\N	\N	\N
51	Pepito Perez	pepito@gmail.com	$2a$10$DDHp2uuvBZJt6i.3rZYsVe8LgyeOvX6y1.WCfvbDQvdJPIpGJYkJq	4	1	2022-07-29 00:31:15.590071	2022-07-29 00:31:15.590071	\N	\N	\N	\N	\N	\N	\N
27	Nora Gallardo	nora@gmail.com	$2a$10$jQBrxW.1qTn93rgZZ9Mq3evkWmYpLGv/8hH34T9quSEaAuGEMYTWa	3	1	2022-07-27 23:20:35.732333	2022-07-27 23:20:35.732333	2022-07-28	M	sadsadas	32432432	2	3	\N
\.


--
-- Data for Name: user_rol; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY core.user_rol (id_user_rol, id_user, id_rol) FROM stdin;
\.


--
-- Data for Name: courses; Type: TABLE DATA; Schema: operations; Owner: postgres
--

COPY operations.courses (id_course, name, description, img, id_user_teacher, duration_time, price, date_init, date_end, call_number, status) FROM stdin;
6	Phyton	\N	string	0	\N	0	2022-07-29	2022-09-22	67128003	0
4	Phyton	\N	string	0	\N	0	2022-07-29	2022-09-22	67128003	0
3	JavaScript Basic	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.		0	4:00hr	4795	2022-08-22	2022-09-22	9876543	1
2	Java Advanced	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.		0	2:00hr	200	2022-07-22	2022-08-22	9876543	1
1	Spring Boot	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.		0	3:00hr	300	2022-07-22	2022-07-22	67128003	1
5	string	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum, versions of Lorem Ipsum.	string	0	string	0	2022-08-21	2022-09-29	67128003	0
7	Laravel	Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.	string	0	3:30Hrs	0	2022-06-30	2022-08-24	67128003	1
\.


--
-- Data for Name: enrolled_courses; Type: TABLE DATA; Schema: operations; Owner: postgres
--

COPY operations.enrolled_courses (id_enrolled_courses, id_course, status, id_user, date_reg, id_payment_plan) FROM stdin;
2	1	1	1	2022-07-24 23:11:03.606886	1
3	2	1	1	2022-07-24 23:16:45.343125	2
4	2	1	2	2022-07-24 23:29:08.638455	3
5	1	1	2	2022-07-24 23:29:25.440623	3
6	3	1	3	2022-07-25 19:25:09.890374	4
7	2	1	3	2022-07-25 19:25:50.160766	2
8	2	1	27	2022-07-29 06:29:00.635873	1
9	2	1	27	2022-07-29 06:29:39.723408	1
10	2	1	27	2022-07-29 06:31:30.99922	3
11	2	1	27	2022-07-29 06:33:39.885888	1
12	1	1	27	2022-07-29 06:33:59.440671	3
\.


--
-- Data for Name: payment_plan; Type: TABLE DATA; Schema: operations; Owner: postgres
--

COPY operations.payment_plan (id_payment_plan, number_installments, interest, status, discount, name_plan) FROM stdin;
1	1	0	1	1500	PIF ( pay in Full)
3	24	5.99	1	0	installments
4	48	11.99	1	0	installments
2	16	0	1	0	installments
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: utilitarian; Owner: postgres
--

COPY utilitarian.country (id_country, name, status, date_reg) FROM stdin;
1	Bolivia	1	2022-07-25 00:26:34.903916
2	Colombia	1	2022-07-25 00:26:34.903916
3	Peru	1	2022-07-25 00:26:34.903916
4	Chile	1	2022-07-25 00:26:34.903916
5	Argentina	1	2022-07-25 00:26:34.903916
6	Mexico	1	2022-07-25 00:26:34.903916
\.


--
-- Data for Name: education_level; Type: TABLE DATA; Schema: utilitarian; Owner: postgres
--

COPY utilitarian.education_level (id_education_level, name, description, status) FROM stdin;
1	Bachelor Degree	\N	1
3	High School Student	\N	1
2	College Student	\N	1
\.


--
-- Data for Name: language; Type: TABLE DATA; Schema: utilitarian; Owner: postgres
--

COPY utilitarian.language (id_language, name, status, date_reg) FROM stdin;
1	Spanish	1	2022-07-25 00:27:57.366691
2	English	1	2022-07-25 00:27:57.366691
3	French	1	2022-07-25 00:27:57.366691
\.


--
-- Name: rol_id_rol_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('core.rol_id_rol_seq', 3, true);


--
-- Name: user_id_user_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('core.user_id_user_seq', 51, true);


--
-- Name: user_rol_id_user_rol_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('core.user_rol_id_user_rol_seq', 1, false);


--
-- Name: courses_id_course_seq; Type: SEQUENCE SET; Schema: operations; Owner: postgres
--

SELECT pg_catalog.setval('operations.courses_id_course_seq', 7, true);


--
-- Name: enrolled_courses_id_enrolled_courses_seq; Type: SEQUENCE SET; Schema: operations; Owner: postgres
--

SELECT pg_catalog.setval('operations.enrolled_courses_id_enrolled_courses_seq', 12, true);


--
-- Name: payment_plan_id_payment_plan_seq; Type: SEQUENCE SET; Schema: operations; Owner: postgres
--

SELECT pg_catalog.setval('operations.payment_plan_id_payment_plan_seq', 4, true);


--
-- Name: country_id_country_seq; Type: SEQUENCE SET; Schema: utilitarian; Owner: postgres
--

SELECT pg_catalog.setval('utilitarian.country_id_country_seq', 6, true);


--
-- Name: edutation_level_id_education_level_seq; Type: SEQUENCE SET; Schema: utilitarian; Owner: postgres
--

SELECT pg_catalog.setval('utilitarian.edutation_level_id_education_level_seq', 3, true);


--
-- Name: language_id_languaje_seq; Type: SEQUENCE SET; Schema: utilitarian; Owner: postgres
--

SELECT pg_catalog.setval('utilitarian.language_id_languaje_seq', 3, true);


--
-- Name: rol rol_pk; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.rol
    ADD CONSTRAINT rol_pk PRIMARY KEY (id_rol);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id_user);


--
-- Name: courses courses_pk; Type: CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.courses
    ADD CONSTRAINT courses_pk PRIMARY KEY (id_course);


--
-- Name: enrolled_courses enrolled_courses_pk; Type: CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.enrolled_courses
    ADD CONSTRAINT enrolled_courses_pk PRIMARY KEY (id_enrolled_courses);


--
-- Name: payment_plan payment_plan_pk; Type: CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.payment_plan
    ADD CONSTRAINT payment_plan_pk PRIMARY KEY (id_payment_plan);


--
-- Name: country country_pk; Type: CONSTRAINT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.country
    ADD CONSTRAINT country_pk PRIMARY KEY (id_country);


--
-- Name: education_level edutation_level_pk; Type: CONSTRAINT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.education_level
    ADD CONSTRAINT edutation_level_pk PRIMARY KEY (id_education_level);


--
-- Name: language language_pk; Type: CONSTRAINT; Schema: utilitarian; Owner: postgres
--

ALTER TABLE ONLY utilitarian.language
    ADD CONSTRAINT language_pk PRIMARY KEY (id_language);


--
-- Name: rol_id_rol_uindex; Type: INDEX; Schema: core; Owner: postgres
--

CREATE UNIQUE INDEX rol_id_rol_uindex ON core.rol USING btree (id_rol);


--
-- Name: user_id_user_uindex; Type: INDEX; Schema: core; Owner: postgres
--

CREATE UNIQUE INDEX user_id_user_uindex ON core."user" USING btree (id_user);


--
-- Name: user_rol_id_user_rol_uindex; Type: INDEX; Schema: core; Owner: postgres
--

CREATE UNIQUE INDEX user_rol_id_user_rol_uindex ON core.user_rol USING btree (id_user_rol);


--
-- Name: courses_id_course_uindex; Type: INDEX; Schema: operations; Owner: postgres
--

CREATE UNIQUE INDEX courses_id_course_uindex ON operations.courses USING btree (id_course);


--
-- Name: enrolled_courses_id_enrolled_courses_uindex; Type: INDEX; Schema: operations; Owner: postgres
--

CREATE UNIQUE INDEX enrolled_courses_id_enrolled_courses_uindex ON operations.enrolled_courses USING btree (id_enrolled_courses);


--
-- Name: payment_plan_id_payment_plan_uindex; Type: INDEX; Schema: operations; Owner: postgres
--

CREATE UNIQUE INDEX payment_plan_id_payment_plan_uindex ON operations.payment_plan USING btree (id_payment_plan);


--
-- Name: country_id_country_uindex; Type: INDEX; Schema: utilitarian; Owner: postgres
--

CREATE UNIQUE INDEX country_id_country_uindex ON utilitarian.country USING btree (id_country);


--
-- Name: edutation_level_id_education_level_uindex; Type: INDEX; Schema: utilitarian; Owner: postgres
--

CREATE UNIQUE INDEX edutation_level_id_education_level_uindex ON utilitarian.education_level USING btree (id_education_level);


--
-- Name: language_id_languaje_uindex; Type: INDEX; Schema: utilitarian; Owner: postgres
--

CREATE UNIQUE INDEX language_id_languaje_uindex ON utilitarian.language USING btree (id_language);


--
-- Name: user user_country_id_country_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core."user"
    ADD CONSTRAINT user_country_id_country_fk FOREIGN KEY (id_country) REFERENCES utilitarian.country(id_country);


--
-- Name: user user_edutation_level_id_education_level_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core."user"
    ADD CONSTRAINT user_edutation_level_id_education_level_fk FOREIGN KEY (id_education) REFERENCES utilitarian.education_level(id_education_level);


--
-- Name: user user_language_id_languaje_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core."user"
    ADD CONSTRAINT user_language_id_languaje_fk FOREIGN KEY (id_language) REFERENCES utilitarian.language(id_language);


--
-- Name: user_rol user_rol_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.user_rol
    ADD CONSTRAINT user_rol_fk FOREIGN KEY (id_rol) REFERENCES core.rol(id_rol);


--
-- Name: user_rol user_rol_user_id_user_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY core.user_rol
    ADD CONSTRAINT user_rol_user_id_user_fk FOREIGN KEY (id_user) REFERENCES core."user"(id_user);


--
-- Name: enrolled_courses enrolled_courses_courses_id_course_fk; Type: FK CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.enrolled_courses
    ADD CONSTRAINT enrolled_courses_courses_id_course_fk FOREIGN KEY (id_course) REFERENCES operations.courses(id_course);


--
-- Name: enrolled_courses enrolled_courses_payment_plan_id_payment_plan_fk; Type: FK CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.enrolled_courses
    ADD CONSTRAINT enrolled_courses_payment_plan_id_payment_plan_fk FOREIGN KEY (id_payment_plan) REFERENCES operations.payment_plan(id_payment_plan);


--
-- Name: enrolled_courses enrolled_courses_user_id_user_fk; Type: FK CONSTRAINT; Schema: operations; Owner: postgres
--

ALTER TABLE ONLY operations.enrolled_courses
    ADD CONSTRAINT enrolled_courses_user_id_user_fk FOREIGN KEY (id_user) REFERENCES core."user"(id_user);


--
-- PostgreSQL database dump complete
--

