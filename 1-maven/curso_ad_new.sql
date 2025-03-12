CREATE TABLE public.books (
                              id integer NOT NULL,
                              title character varying(255) NOT NULL,
                              genre character varying(100),
                              description text,
                              author character varying(255),
                              publisher character varying(255),
                              pages integer,
                              image_url character varying(255),
                              publish_date date
);


CREATE SEQUENCE public.books_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




ALTER SEQUENCE public.books_id_seq OWNER TO postgres;

--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 214
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.id;


--
-- TOC entry 3199 (class 2604 OID 84892)
-- Name: books id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.books ALTER COLUMN id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- TOC entry 3345 (class 0 OID 84889)
-- Dependencies: 215
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.books VALUES (1, 'Cien años de soledad', 'Novela', 'Una de las obras más importantes de la literatura hispanoamericana', 'Gabriel García Márquez', 'Sudamericana', 471, 'https://example.com/cien_anos.jpg', NULL);
INSERT INTO public.books VALUES (2, '1984', 'Distopía', 'Un mundo totalitario vigilado por el Gran Hermano', 'George Orwell', 'Secker & Warburg', 328, 'https://example.com/1984.jpg', NULL);
INSERT INTO public.books VALUES (3, 'El Principito', 'Fábula', 'Una historia filosófica con un trasfondo poético', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', 96, 'https://example.com/principito.jpg', NULL);


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 214
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.books_id_seq', 3, true);


--
-- TOC entry 3201 (class 2606 OID 84896)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


-- Completed on 2025-03-11 13:58:30 CET

--
-- PostgreSQL database dump complete
--