--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-04-30 23:22:34 BOT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12435)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 238 (class 1255 OID 16890)
-- Name: count_acccat1(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_acccat1(pbusqueda character, pcodrol character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from acccat1 a, bvtcat1 b
where a.b_codcat1 = b.codcat1
and  a.b_codcat1||b.descat1  like '%'||pbusqueda||'%'
AND  a.b_codrol like pcodrol||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_acccat1(pbusqueda character, pcodrol character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 238
-- Name: FUNCTION count_acccat1(pbusqueda character, pcodrol character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_acccat1(pbusqueda character, pcodrol character, pinstancia character) IS 'Cuenta registros de acceso a categoria1';


--
-- TOC entry 240 (class 1255 OID 16891)
-- Name: count_acccat2(character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from acccat2 a, bvtcat1 b, bvtcat2 c
WHERE a.b_codcat1=b.codcat1
and  a.b_codcat1=c.b_codcat1
and  a.b_codcat2=c.codcat2
and  a.b_codcat1||b.descat1||a.b_codcat2||c.descat2 like '%'||pbusqueda||'%'
and  a.b_codcat1 like pcodcat1||'%'
AND  a.b_codrol like pcodrol||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 240
-- Name: FUNCTION count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character) IS 'Cuenta registros de acceso a categoria2';


--
-- TOC entry 209 (class 1255 OID 16892)
-- Name: count_acccat3(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from   acccat3 a, bvtcat1 b, bvtcat2 c, bvtcat3 d
where a.b_codcat1=b.codcat1
and   a.b_codcat1=c.b_codcat1
and   a.b_codcat2=c.codcat2
and   a.b_codcat1=d.b_codcat1
and   a.b_codcat2=d.b_codcat2
and   a.b_codcat3=d.codcat3
and   a.b_codcat1||b.descat1||a.b_codcat2||c.descat2||a.b_codcat3||d.descat3 like '%'||pbusqueda||'%'
and  a.b_codcat1 like pcodcat1||'%'
and  a.b_codcat1 like pcodcat2||'%'
AND  a.b_codrol like pcodrol||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2406 (class 0 OID 0)
-- Dependencies: 209
-- Name: FUNCTION count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character) IS 'Cuenta registros de acceso a categoria3';


--
-- TOC entry 223 (class 1255 OID 16893)
-- Name: count_acccat4(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from acccat4 a, bvtcat1 b, bvtcat2 c, bvtcat3 d, bvtcat4 e
where a.b_codcat1=b.codcat1
and   a.b_codcat1=c.b_codcat1
and   a.b_codcat2=c.codcat2
and   a.b_codcat1=d.b_codcat1
and   a.b_codcat2=d.b_codcat2
and   a.b_codcat3=d.codcat3
and   a.b_codcat1=e.b_codcat1
and   a.b_codcat2=e.b_codcat2
and   a.b_codcat3=e.b_codcat3
and   a.b_codcat4=e.codcat4
and   a.b_codcat1||b.descat1||a.b_codcat2||c.descat2||a.b_codcat3||d.descat3||a.b_codcat4||e.descat4 like  '%'||pbusqueda||'%'
and   a.b_codcat1 like pcodcat1||'%'
and   a.b_codcat2 like pcodcat2||'%'
and   a.b_codcat3 like pcodcat3||'%'
AND   a.b_codrol like pcodrol||'%';
return vcount;
end;$$;


ALTER FUNCTION public.count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character) OWNER TO openbizview;

--
-- TOC entry 2407 (class 0 OID 0)
-- Dependencies: 223
-- Name: FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character) IS 'Cuenta registros de acceso a categoria4';


--
-- TOC entry 242 (class 1255 OID 16894)
-- Name: count_acccat4(character, character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from acccat4 a, bvtcat1 b, bvtcat2 c, bvtcat3 d, bvtcat4 e
where a.b_codcat1=b.codcat1
and   a.b_codcat1=c.b_codcat1
and   a.b_codcat2=c.codcat2
and   a.b_codcat1=d.b_codcat1
and   a.b_codcat2=d.b_codcat2
and   a.b_codcat3=d.codcat3
and   a.b_codcat1=e.b_codcat1
and   a.b_codcat2=e.b_codcat2
and   a.b_codcat3=e.b_codcat3
and   a.b_codcat4=e.codcat4
and   a.b_codcat1||b.descat1||a.b_codcat2||c.descat2||a.b_codcat3||d.descat3||a.b_codcat4||e.descat4 like  '%'||pbusqueda||'%'
and   a.b_codcat1 like pcodcat1||'%'
and   a.b_codcat2 like pcodcat2||'%'
and   a.b_codcat3 like pcodcat3||'%'
AND   a.b_codrol like pcodrol||'%'
and   cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2408 (class 0 OID 0)
-- Dependencies: 242
-- Name: FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character) IS 'Cuenta registros de acceso a categoria4';


--
-- TOC entry 224 (class 1255 OID 16895)
-- Name: count_biaudit(character, date, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
if pvalor = '0' then
select count(*) into vcount 
from biaudit
where  fechadia||substr(fecacc,12,22)||detfaz||negocio like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
else 
select count(*) into vcount 
from biaudit
where  fechadia||substr(fecacc,12,22)||detfaz||negocio like '%'||pbusqueda||'%'
and fechadia = pfecha
and cast(instancia as text) = pinstancia;
end if;
return vcount;
end;$$;


ALTER FUNCTION public.count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 224
-- Name: FUNCTION count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character) IS 'Cuenta registros de auditoria';


--
-- TOC entry 225 (class 1255 OID 16896)
-- Name: count_bvt001(character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
if pgrupo is null then
select count(*) into vcount 
from bvt001
where codrep||desrep  like '%'||pbusqueda||'%'
AND  CODREP  IN (SELECT B_CODREP FROM BVT007 WHERE B_CODROL = pcodrol);
else
select count(*) into vcount 
from bvt001
where codrep||desrep  like '%'||pbusqueda||'%'
AND  CODREP  IN (SELECT B_CODREP FROM BVT007 WHERE B_CODROL = pcodrol)
and  codgrup like pgrupo||'%'
and cast(instancia as text) = pinstancia;
end if;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2410 (class 0 OID 0)
-- Dependencies: 225
-- Name: FUNCTION count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character) IS 'Cuenta registros de reportes';


--
-- TOC entry 226 (class 1255 OID 16897)
-- Name: count_bvt001a(character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt001a(pbusqueda character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvt001a
where codgrup||desgrup  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt001a(pbusqueda character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2411 (class 0 OID 0)
-- Dependencies: 226
-- Name: FUNCTION count_bvt001a(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt001a(pbusqueda character, pinstancia character) IS 'Cuenta registros de reportes';


--
-- TOC entry 227 (class 1255 OID 16898)
-- Name: count_bvt002(character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt002(pbusqueda character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvt002
where coduser||desuser  like '%'||pbusqueda||'%';
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt002(pbusqueda character) OWNER TO openbizview;

--
-- TOC entry 2412 (class 0 OID 0)
-- Dependencies: 227
-- Name: FUNCTION count_bvt002(pbusqueda character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt002(pbusqueda character) IS 'Cuenta registros de usuario';


--
-- TOC entry 228 (class 1255 OID 16899)
-- Name: count_bvt003(character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt003(pbusqueda character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvt003
where codrol||desrol  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt003(pbusqueda character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2413 (class 0 OID 0)
-- Dependencies: 228
-- Name: FUNCTION count_bvt003(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt003(pbusqueda character, pinstancia character) IS 'Cuenta registros de roles';


--
-- TOC entry 232 (class 1255 OID 16900)
-- Name: count_bvt005(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt005(pbusqueda character, prol character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM Bvt005
WHERE b_codrol||codopc||desopc like  '%'||pbusqueda||'%'
and b_codrol like prol||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt005(pbusqueda character, prol character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 232
-- Name: FUNCTION count_bvt005(pbusqueda character, prol character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt005(pbusqueda character, prol character, pinstancia character) IS 'Cuenta registros de acceso a botones';


--
-- TOC entry 229 (class 1255 OID 16901)
-- Name: count_bvt006(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt006(pbusqueda character, pcoduser character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvt006
where b_codrep||b_desrep  like '%'||pbusqueda||'%'
and b_coduser like pcoduser||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt006(pbusqueda character, pcoduser character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 229
-- Name: FUNCTION count_bvt006(pbusqueda character, pcoduser character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt006(pbusqueda character, pcoduser character, pinstancia character) IS 'Cuenta registros de auditoría';


--
-- TOC entry 233 (class 1255 OID 16902)
-- Name: count_bvt007(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvt007(pbusqueda character, prol character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM Bvt007 a, bvt003 b, bvt001 c
WHERE a.b_codrol=b.codrol
and   a.b_codrep=c.codrep
and a.b_codrol||b.desrol||a.b_codrep||c.desrep like  '%'||pbusqueda||'%'
and a.b_codrol like prol||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt007(pbusqueda character, prol character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 233
-- Name: FUNCTION count_bvt007(pbusqueda character, prol character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvt007(pbusqueda character, prol character, pinstancia character) IS 'Cuenta registros de acceso a reportes';


--
-- TOC entry 230 (class 1255 OID 16903)
-- Name: count_bvtcat1(character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvtcat1(pbusqueda character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtcat1
where codcat1||descat1  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvtcat1(pbusqueda character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2417 (class 0 OID 0)
-- Dependencies: 230
-- Name: FUNCTION count_bvtcat1(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvtcat1(pbusqueda character, pinstancia character) IS 'Cuenta registros de categoria1';


--
-- TOC entry 234 (class 1255 OID 16904)
-- Name: count_bvtcat2(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtcat2
where b_codcat1 like pbcodcat1||'%' 
and codcat2||descat2  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 234
-- Name: FUNCTION count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character) IS 'Cuenta registros de auditoria de reporte impresos';


--
-- TOC entry 231 (class 1255 OID 16905)
-- Name: count_bvtcat3(character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtcat3
where b_codcat1 like pbcodcat1||'%'
and b_codcat2 like pbcodcat2||'%' 
and codcat3||descat3  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 231
-- Name: FUNCTION count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character) IS 'Cuenta registros de categoria 3';


--
-- TOC entry 222 (class 1255 OID 16906)
-- Name: count_bvtcat4(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtcat4
where b_codcat1 like pbcodcat1||'%'
and b_codcat2 like pbcodcat2||'%' 
and b_codcat3 like pbcodcat3||'%' 
and codcat4||descat4  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 222
-- Name: FUNCTION count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character) IS 'Cuenta registros de categoria 4';


--
-- TOC entry 239 (class 1255 OID 16907)
-- Name: count_bvtmenu(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtmenu
where b_codrol||codopc||desopc  like '%'||pbusqueda||'%'
AND  b_codrol  like pcodrol||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 239
-- Name: FUNCTION count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character) IS 'Cuenta registros de opciones roles';


--
-- TOC entry 208 (class 1255 OID 16908)
-- Name: count_instancias(character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_instancias(pbusqueda character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from instancias
where cast(instancia as text)||descripcion  like '%'||pbusqueda||'%';
return vcount;
end;$$;


ALTER FUNCTION public.count_instancias(pbusqueda character) OWNER TO openbizview;

--
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 208
-- Name: FUNCTION count_instancias(pbusqueda character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_instancias(pbusqueda character) IS 'Cuenta registros de instancias';


--
-- TOC entry 235 (class 1255 OID 16909)
-- Name: count_instanciasusr(character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_instanciasusr(pbusqueda character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from instancias_usr a, instancias b
where a.instancia=b.instancia
and  a.coduser||cast(a.instancia as text)||b.descripcion  like '%'||pbusqueda||'%'
AND  cast(a.instancia as text) like pinstancia||'%';
return vcount;
end;$$;


ALTER FUNCTION public.count_instanciasusr(pbusqueda character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 235
-- Name: FUNCTION count_instanciasusr(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_instanciasusr(pbusqueda character, pinstancia character) IS 'Cuenta registros de instancias usuarios';


--
-- TOC entry 236 (class 1255 OID 16910)
-- Name: count_mailgrupo(character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_mailgrupo(pbusqueda character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from mailgrupos
where cast(idgrupo as text)||desgrupo  like '%'||pbusqueda||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_mailgrupo(pbusqueda character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 236
-- Name: FUNCTION count_mailgrupo(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_mailgrupo(pbusqueda character, pinstancia character) IS 'Cuenta registros de mailgrupo';


--
-- TOC entry 243 (class 1255 OID 16911)
-- Name: count_maillista(character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  MAILGRUPOS A, MAILLISTA B
WHERE A.IDGRUPO=B.IDGRUPO
and a.instancia = b.instancia
and cast(a.idgrupo as text)||cast(b.idmail as text)||UPPER(b.mail) like  '%'||pbusqueda||'%'
and  cast(a.idgrupo as text) like pidgrupo||'%'
and cast(b.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_maillista(pbusqueda character, pidgrupo character, pinstancia character) OWNER TO openbizview;

--
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 243
-- Name: FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character) IS 'Cuenta registros de lista de correos';


--
-- TOC entry 241 (class 1255 OID 24622)
-- Name: count_programacion(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION count_programacion(pbusqueda character, pinstancia character, preporte character, pfrecuencia character, pgrupo character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||codrep like  '%'||pbusqueda||'%'
and codrep like preporte||'%'
and frecuencia like pfrecuencia||'%'
and cast(idgrupo as text) like pgrupo||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_programacion(pbusqueda character, pinstancia character, preporte character, pfrecuencia character, pgrupo character) OWNER TO openbizview;

--
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 241
-- Name: FUNCTION count_programacion(pbusqueda character, pinstancia character, preporte character, pfrecuencia character, pgrupo character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION count_programacion(pbusqueda character, pinstancia character, preporte character, pfrecuencia character, pgrupo character) IS 'Cuenta registros de programación';


--
-- TOC entry 237 (class 1255 OID 16913)
-- Name: fn_desgrup(character); Type: FUNCTION; Schema: public; Owner: openbizview
--

CREATE FUNCTION fn_desgrup(pcodgrup character) RETURNS character
    LANGUAGE plpgsql
    AS $$declare
vdesgrup character(50);
begin
select desgrup into vdesgrup 
from bvt001a
where codgrup = pcodgrup;
return vdesgrup;
end;$$;


ALTER FUNCTION public.fn_desgrup(pcodgrup character) OWNER TO openbizview;

--
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 237
-- Name: FUNCTION fn_desgrup(pcodgrup character); Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON FUNCTION fn_desgrup(pcodgrup character) IS 'Retorna la descripción del grupo de reportes';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 16914)
-- Name: acccat1; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE acccat1 (
    b_codrol character(5) NOT NULL,
    b_codcat1 character(10) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE acccat1 OWNER TO openbizview;

--
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 181
-- Name: TABLE acccat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE acccat1 IS 'Acceso a Categoria1';


--
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.b_codrol IS 'Código del rol';


--
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN acccat1.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat1.instancia IS 'Instancia de usuario';


--
-- TOC entry 182 (class 1259 OID 16917)
-- Name: acccat2; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE acccat2 (
    b_codrol character(5) NOT NULL,
    b_codcat1 character(10) NOT NULL,
    b_codcat2 character(10) NOT NULL,
    usrcre character(100),
    feccre date,
    usract character(100),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE acccat2 OWNER TO openbizview;

--
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 182
-- Name: TABLE acccat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE acccat2 IS 'Acceso a Categoria2';


--
-- TOC entry 2437 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.b_codrol IS 'Código del rol';


--
-- TOC entry 2438 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2439 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.b_codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2440 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2441 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2442 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2443 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2444 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN acccat2.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat2.instancia IS 'Instancia de usuario';


--
-- TOC entry 183 (class 1259 OID 16920)
-- Name: acccat3; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE acccat3 (
    b_codrol character(5) NOT NULL,
    b_codcat1 character(10) NOT NULL,
    b_codcat2 character(10) NOT NULL,
    b_codcat3 character(10) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE acccat3 OWNER TO openbizview;

--
-- TOC entry 2445 (class 0 OID 0)
-- Dependencies: 183
-- Name: TABLE acccat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE acccat3 IS 'Acceso a Categoria3';


--
-- TOC entry 2446 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.b_codrol IS 'Código del rol';


--
-- TOC entry 2447 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2448 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.b_codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2449 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.b_codcat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.b_codcat3 IS 'Código categoria3';


--
-- TOC entry 2450 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2451 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2452 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2453 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2454 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN acccat3.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat3.instancia IS 'Instancia de usuario';


--
-- TOC entry 184 (class 1259 OID 16923)
-- Name: acccat4; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE acccat4 (
    b_codrol character(5) NOT NULL,
    b_codcat1 character(10) NOT NULL,
    b_codcat2 character(10) NOT NULL,
    b_codcat3 character(10) NOT NULL,
    b_codcat4 character(10) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE acccat4 OWNER TO openbizview;

--
-- TOC entry 2455 (class 0 OID 0)
-- Dependencies: 184
-- Name: TABLE acccat4; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE acccat4 IS 'Acceso a Categoria4';


--
-- TOC entry 2456 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.b_codrol IS 'Código del rol';


--
-- TOC entry 2457 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2458 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.b_codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2459 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.b_codcat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.b_codcat3 IS 'Código categoria3';


--
-- TOC entry 2460 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.b_codcat4; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.b_codcat4 IS 'Código categoria4';


--
-- TOC entry 2461 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2462 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2463 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2464 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2465 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN acccat4.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN acccat4.instancia IS 'Instancia de usuario';


--
-- TOC entry 185 (class 1259 OID 16926)
-- Name: biaudit; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE biaudit (
    fecacc character(30) NOT NULL,
    detfaz character(500) NOT NULL,
    result character(20) NOT NULL,
    negocio character(20),
    fechadia date NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE biaudit OWNER TO openbizview;

--
-- TOC entry 2466 (class 0 OID 0)
-- Dependencies: 185
-- Name: TABLE biaudit; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE biaudit IS 'Detalle del log de interfaces';


--
-- TOC entry 2467 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.fecacc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.fecacc IS 'Fecha de la interfaz';


--
-- TOC entry 2468 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.detfaz; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.detfaz IS 'Descripción de la interfaz';


--
-- TOC entry 2469 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.result; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.result IS 'Resultado de la interfaz';


--
-- TOC entry 2470 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.negocio; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.negocio IS 'Negocio con el que se realiza la interfaz';


--
-- TOC entry 2471 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.fechadia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.fechadia IS 'Fecha de la interfaz';


--
-- TOC entry 2472 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN biaudit.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN biaudit.instancia IS 'Instancia de usuarios';


--
-- TOC entry 186 (class 1259 OID 16932)
-- Name: bvt001; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt001 (
    codrep character(15) NOT NULL,
    desrep character(50) NOT NULL,
    comrep character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    codgrup character(10) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE bvt001 OWNER TO openbizview;

--
-- TOC entry 2473 (class 0 OID 0)
-- Dependencies: 186
-- Name: TABLE bvt001; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt001 IS 'Reportes';


--
-- TOC entry 2474 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.codrep IS 'Código de reporte';


--
-- TOC entry 2475 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.desrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.desrep IS 'Descripción de reporte';


--
-- TOC entry 2476 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.comrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.comrep IS 'Comentario de reporte';


--
-- TOC entry 2477 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2478 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2479 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.usract IS 'Usuario de creación de registro';


--
-- TOC entry 2480 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2481 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.codgrup; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.codgrup IS 'Código de grupo';


--
-- TOC entry 2482 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvt001.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001.instancia IS 'Instancia';


--
-- TOC entry 187 (class 1259 OID 16935)
-- Name: bvt001a; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt001a (
    codgrup character(10) NOT NULL,
    desgrup character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvt001a OWNER TO openbizview;

--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 187
-- Name: TABLE bvt001a; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt001a IS 'Agrupación de reportes';


--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.codgrup; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.codgrup IS 'Código del grupo';


--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.desgrup; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.desgrup IS 'Descripción del grupo';


--
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.feccre IS 'Fecha de creación de registros';


--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.usract IS 'Usuario de actualización de registros';


--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.fecact IS 'Fecha de actualización de registros';


--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvt001a.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt001a.instancia IS 'Instancia usuarios';


--
-- TOC entry 188 (class 1259 OID 16938)
-- Name: bvt002; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt002 (
    coduser character(10) NOT NULL,
    desuser character(50) NOT NULL,
    cluser character(150) NOT NULL,
    b_codrol character(5) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    mail character(150) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE bvt002 OWNER TO openbizview;

--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE bvt002; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt002 IS 'Usuarios';


--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.coduser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.coduser IS 'Usuario';


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.desuser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.desuser IS 'Descripción de usuario';


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.cluser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.cluser IS 'Clave de usuario';


--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.b_codrol IS 'Rol de usuario';


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2498 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2500 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.mail; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.mail IS 'Dirección de correo electrónico';


--
-- TOC entry 2501 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvt002.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt002.instancia IS 'Instancia asociada al usuario';


--
-- TOC entry 189 (class 1259 OID 16942)
-- Name: bvt003; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt003 (
    codrol character(5) NOT NULL,
    desrol character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvt003 OWNER TO openbizview;

--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 189
-- Name: TABLE bvt003; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt003 IS 'Roles';


--
-- TOC entry 2503 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.codrol IS 'Código de rol';


--
-- TOC entry 2504 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.desrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.desrol IS 'Descripción del rol';


--
-- TOC entry 2505 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2506 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2507 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2508 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2509 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvt003.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt003.instancia IS 'Instancia de usuario';


--
-- TOC entry 190 (class 1259 OID 16945)
-- Name: bvt005; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt005 (
    b_codrol character(5) NOT NULL,
    codopc character(2) NOT NULL,
    desopc character(50) NOT NULL,
    codvis character(1) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvt005 OWNER TO openbizview;

--
-- TOC entry 2510 (class 0 OID 0)
-- Dependencies: 190
-- Name: TABLE bvt005; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt005 IS 'Acceso a Botónes';


--
-- TOC entry 2511 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.b_codrol IS 'Código del rol';


--
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.codopc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.codopc IS 'Número de opción';


--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.desopc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.desopc IS 'Descripción de opción';


--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.codvis; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.codvis IS 'Visualiza 1 - No visualiza 0';


--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvt005.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt005.instancia IS 'Grupo de usuario';


--
-- TOC entry 191 (class 1259 OID 16948)
-- Name: bvt006; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt006 (
    b_codrep character(15) NOT NULL,
    b_desrep character(50) NOT NULL,
    b_coduser character(100) NOT NULL,
    fecacc timestamp(6) without time zone NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE bvt006 OWNER TO openbizview;

--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 191
-- Name: TABLE bvt006; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt006 IS 'Log de reportes impresos';


--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvt006.b_codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt006.b_codrep IS 'Código de reporte';


--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvt006.b_desrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt006.b_desrep IS 'Descripción de reporte';


--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvt006.b_coduser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt006.b_coduser IS 'Usuario que imprime reporte';


--
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvt006.fecacc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt006.fecacc IS 'Fecha de registro';


--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvt006.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt006.instancia IS 'Instancia de usuario';


--
-- TOC entry 192 (class 1259 OID 16951)
-- Name: bvt007; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt007 (
    b_codrol character(5) NOT NULL,
    b_codrep character(15) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvt007 OWNER TO openbizview;

--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 192
-- Name: TABLE bvt007; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt007 IS 'Acceso a Reportes';


--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.b_codrol IS 'Código del rol';


--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.b_codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.b_codrep IS 'Código de reporte';


--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN bvt007.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt007.instancia IS 'Instancia usuarios';


--
-- TOC entry 207 (class 1259 OID 24727)
-- Name: bvt008; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvt008 (
    coduser character(10) NOT NULL,
    b_codrol character(5) NOT NULL,
    usrcre character(10),
    feccre date,
    instancia integer NOT NULL
);


ALTER TABLE bvt008 OWNER TO openbizview;

--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE bvt008; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvt008 IS 'Roles adicionales';


--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN bvt008.coduser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt008.coduser IS 'Código de usuario';


--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN bvt008.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt008.b_codrol IS 'Código del rol';


--
-- TOC entry 2537 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN bvt008.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt008.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2538 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN bvt008.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt008.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2539 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN bvt008.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvt008.instancia IS 'Instancia de usuario';


--
-- TOC entry 193 (class 1259 OID 16954)
-- Name: bvtcat1; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtcat1 (
    codcat1 character(10) NOT NULL,
    descat1 character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvtcat1 OWNER TO openbizview;

--
-- TOC entry 2540 (class 0 OID 0)
-- Dependencies: 193
-- Name: TABLE bvtcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtcat1 IS 'Categoria1';


--
-- TOC entry 2541 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2542 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.descat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.descat1 IS 'Decripción de categoria1';


--
-- TOC entry 2543 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2544 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2545 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2546 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2547 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN bvtcat1.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat1.instancia IS 'Instancias';


--
-- TOC entry 194 (class 1259 OID 16957)
-- Name: bvtcat2; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtcat2 (
    b_codcat1 character(10) NOT NULL,
    codcat2 character(10) NOT NULL,
    descat2 character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvtcat2 OWNER TO openbizview;

--
-- TOC entry 2548 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE bvtcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtcat2 IS 'Categoria2';


--
-- TOC entry 2549 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2550 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.codcat2 IS 'Código categoria2';


--
-- TOC entry 2551 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.descat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.descat2 IS 'Descripción de categoria 2';


--
-- TOC entry 2552 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.usrcre IS 'Usuario de cración de registro';


--
-- TOC entry 2553 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2554 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2555 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2556 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN bvtcat2.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat2.instancia IS 'Instancia de usuario';


--
-- TOC entry 195 (class 1259 OID 16960)
-- Name: bvtcat3; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtcat3 (
    b_codcat1 character(10) NOT NULL,
    b_codcat2 character(10) NOT NULL,
    codcat3 character(10) NOT NULL,
    descat3 character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvtcat3 OWNER TO openbizview;

--
-- TOC entry 2557 (class 0 OID 0)
-- Dependencies: 195
-- Name: TABLE bvtcat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtcat3 IS 'Categoria3';


--
-- TOC entry 2558 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2559 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.b_codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.b_codcat2 IS 'Código de categoria 2';


--
-- TOC entry 2560 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.codcat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.codcat3 IS 'Código de categoria 3';


--
-- TOC entry 2561 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.descat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.descat3 IS 'Decripción de categoria2';


--
-- TOC entry 2562 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2563 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2564 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2565 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2566 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN bvtcat3.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat3.instancia IS 'Instancia de usuario';


--
-- TOC entry 196 (class 1259 OID 16963)
-- Name: bvtcat4; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtcat4 (
    b_codcat1 character(10) NOT NULL,
    b_codcat2 character(10) NOT NULL,
    b_codcat3 character(10) NOT NULL,
    codcat4 character(10) NOT NULL,
    descat4 character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    equicat4 character(10),
    tippro character(1),
    instancia integer NOT NULL
);


ALTER TABLE bvtcat4 OWNER TO openbizview;

--
-- TOC entry 2567 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE bvtcat4; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtcat4 IS 'Categoria4';


--
-- TOC entry 2568 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.b_codcat1; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2569 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.b_codcat2; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.b_codcat2 IS 'Código de categoria 2';


--
-- TOC entry 2570 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.b_codcat3; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.b_codcat3 IS 'Código de categoria 3';


--
-- TOC entry 2571 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2572 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2573 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2574 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2575 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.equicat4; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.equicat4 IS 'Equivalencia de categoria 4';


--
-- TOC entry 2576 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN bvtcat4.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtcat4.instancia IS 'Instancia de usuario';


--
-- TOC entry 197 (class 1259 OID 16966)
-- Name: bvtmenu; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtmenu (
    b_codrol character(5) NOT NULL,
    codopc character(4) NOT NULL,
    desopc character(50) NOT NULL,
    codvis character(1) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvtmenu OWNER TO openbizview;

--
-- TOC entry 2577 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE bvtmenu; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtmenu IS 'Menú';


--
-- TOC entry 2578 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.b_codrol; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.b_codrol IS 'Usuario acceso al menú';


--
-- TOC entry 2579 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.codopc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.codopc IS 'Número de opción';


--
-- TOC entry 2580 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.desopc; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.desopc IS 'Descripción de opción';


--
-- TOC entry 2581 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.codvis; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.codvis IS '1-Visualiza, 0-No visualiza';


--
-- TOC entry 2582 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2583 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2584 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2585 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2586 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN bvtmenu.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtmenu.instancia IS 'Instancia usuario';


--
-- TOC entry 198 (class 1259 OID 16969)
-- Name: bvtparams_number_temp; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtparams_number_temp (
    codrep character(10) NOT NULL,
    sessionid character(500) NOT NULL,
    paramnumber integer NOT NULL
);


ALTER TABLE bvtparams_number_temp OWNER TO openbizview;

--
-- TOC entry 2587 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE bvtparams_number_temp; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtparams_number_temp IS 'Temporal de parametros de reportes';


--
-- TOC entry 2588 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN bvtparams_number_temp.codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_number_temp.codrep IS 'Código del reporte';


--
-- TOC entry 2589 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN bvtparams_number_temp.sessionid; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_number_temp.sessionid IS 'Id de la sessión';


--
-- TOC entry 2590 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN bvtparams_number_temp.paramnumber; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_number_temp.paramnumber IS 'Número de parámetros';


--
-- TOC entry 199 (class 1259 OID 16975)
-- Name: bvtparams_temp; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE bvtparams_temp (
    codrep character(10) NOT NULL,
    paramname character(150) NOT NULL,
    paramtype integer NOT NULL,
    sessionid character(500) NOT NULL,
    promptext character(150) NOT NULL,
    paramhidden character(5) NOT NULL,
    paramrequired character(5) NOT NULL
);


ALTER TABLE bvtparams_temp OWNER TO openbizview;

--
-- TOC entry 2591 (class 0 OID 0)
-- Dependencies: 199
-- Name: TABLE bvtparams_temp; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE bvtparams_temp IS 'Temporal de parametros de reportes';


--
-- TOC entry 2592 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.codrep IS 'Código del reporte';


--
-- TOC entry 2593 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.paramname; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.paramname IS 'Nombre del parámetro';


--
-- TOC entry 2594 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.paramtype; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.paramtype IS 'Tipo de parámetro';


--
-- TOC entry 2595 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.sessionid; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.sessionid IS 'Id de la sessión';


--
-- TOC entry 2596 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.promptext; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.promptext IS 'Descripción del parámetro';


--
-- TOC entry 2597 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.paramhidden; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.paramhidden IS 'Indica si el parámetro es hidden';


--
-- TOC entry 2598 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN bvtparams_temp.paramrequired; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN bvtparams_temp.paramrequired IS 'Indica si es requerido';


--
-- TOC entry 200 (class 1259 OID 16981)
-- Name: descargas; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE descargas (
    fecha timestamp(6) without time zone NOT NULL,
    cuenta integer NOT NULL,
    ip character(50) NOT NULL
);


ALTER TABLE descargas OWNER TO openbizview;

--
-- TOC entry 2599 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE descargas; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE descargas IS 'Conteo de descargas';


--
-- TOC entry 2600 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN descargas.fecha; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN descargas.fecha IS 'Fecha y hora de descarga';


--
-- TOC entry 2601 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN descargas.cuenta; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN descargas.cuenta IS 'Veces descargadas';


--
-- TOC entry 2602 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN descargas.ip; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN descargas.ip IS 'Ip de descarga';


--
-- TOC entry 201 (class 1259 OID 16984)
-- Name: instancias; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE instancias (
    instancia integer NOT NULL,
    descripcion character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    CONSTRAINT instancias_chk1 CHECK ((instancia > 0))
);


ALTER TABLE instancias OWNER TO openbizview;

--
-- TOC entry 2603 (class 0 OID 0)
-- Dependencies: 201
-- Name: TABLE instancias; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE instancias IS 'Instancias';


--
-- TOC entry 2604 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.instancia IS 'Código';


--
-- TOC entry 2605 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.descripcion; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.descripcion IS 'Descripción';


--
-- TOC entry 2606 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2607 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2608 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2609 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN instancias.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 202 (class 1259 OID 16988)
-- Name: instancias_usr; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE instancias_usr (
    coduser character(10) NOT NULL,
    instancia integer NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date
);


ALTER TABLE instancias_usr OWNER TO openbizview;

--
-- TOC entry 2610 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE instancias_usr; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE instancias_usr IS 'Asociar instancias a usuarios';


--
-- TOC entry 2611 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.coduser; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.coduser IS 'Código de usuario';


--
-- TOC entry 2612 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.instancia IS 'Instancias';


--
-- TOC entry 2613 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.usrcre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2614 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.feccre; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2615 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.usract; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2616 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN instancias_usr.fecact; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN instancias_usr.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 203 (class 1259 OID 16991)
-- Name: mailgrupos; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE mailgrupos (
    idgrupo integer NOT NULL,
    desgrupo character(50) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE mailgrupos OWNER TO openbizview;

--
-- TOC entry 2617 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE mailgrupos; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE mailgrupos IS 'Grupos de correo';


--
-- TOC entry 2618 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN mailgrupos.idgrupo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN mailgrupos.idgrupo IS 'Identificador de grupo';


--
-- TOC entry 2619 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN mailgrupos.desgrupo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN mailgrupos.desgrupo IS 'Descripción del grupo';


--
-- TOC entry 2620 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN mailgrupos.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN mailgrupos.instancia IS 'Instancia usuario';


--
-- TOC entry 204 (class 1259 OID 16994)
-- Name: maillista; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE maillista (
    idgrupo integer NOT NULL,
    idmail character(50) NOT NULL,
    mail character(100) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE maillista OWNER TO openbizview;

--
-- TOC entry 2621 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE maillista; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE maillista IS 'Listas de correos';


--
-- TOC entry 2622 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN maillista.idgrupo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN maillista.idgrupo IS 'Grupo de correos';


--
-- TOC entry 2623 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN maillista.idmail; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN maillista.idmail IS 'Id de correo';


--
-- TOC entry 2624 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN maillista.mail; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN maillista.mail IS 'Correo electrónico';


--
-- TOC entry 2625 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN maillista.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN maillista.instancia IS 'Insttancia de usuario';


--
-- TOC entry 205 (class 1259 OID 16997)
-- Name: t_programacion; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE t_programacion (
    disparador character(20) NOT NULL,
    grupo character(20) NOT NULL,
    diasem integer NOT NULL,
    frecuencia character(1),
    asunto character(50) NOT NULL,
    contenido character(4000) NOT NULL,
    codrep character(15) NOT NULL,
    rutarep character(1500) NOT NULL,
    rutatemp character(1500) NOT NULL,
    idgrupo integer NOT NULL,
    job character(20) NOT NULL,
    diames character(2) NOT NULL,
    diainicio timestamp without time zone NOT NULL,
    activa character(1) NOT NULL,
    paramvalues character(500),
    intervalo integer NOT NULL,
    paramnames character(500),
    ruta_salida character(500),
    opctareas character(1) NOT NULL,
    formato character(4) NOT NULL,
    instancia integer NOT NULL,
    dias_semana character(30)
);


ALTER TABLE t_programacion OWNER TO openbizview;

--
-- TOC entry 2626 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE t_programacion; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON TABLE t_programacion IS 'Programación de envio de correos';


--
-- TOC entry 2627 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.disparador; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.disparador IS 'Nombre del disparador';


--
-- TOC entry 2628 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.grupo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.grupo IS 'Nombre del grupo de la tarea';


--
-- TOC entry 2629 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.diasem; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.diasem IS 'Día de la semana';


--
-- TOC entry 2630 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.frecuencia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.frecuencia IS 'Frecuencia de ejecución, 0-Diaria, 1-Semanal, 2-Personalizada, 3-Mensual, 4-Bimensual, 5-Trimestral';


--
-- TOC entry 2631 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.asunto; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.asunto IS 'Asunto del correo';


--
-- TOC entry 2632 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.contenido; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.contenido IS 'Contenido del correo';


--
-- TOC entry 2633 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.codrep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.codrep IS 'Código de reporte a enviar';


--
-- TOC entry 2634 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.rutarep; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.rutarep IS 'Ruta de ubicación del reporte';


--
-- TOC entry 2635 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.rutatemp; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.rutatemp IS 'Ruta de generación temporal del reporte';


--
-- TOC entry 2636 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.idgrupo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.idgrupo IS 'Id grupo de lista de correos';


--
-- TOC entry 2637 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.job; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.job IS 'Nombre de la tarea o job';


--
-- TOC entry 2638 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.diames; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.diames IS 'Día del mes para envío personalizados';


--
-- TOC entry 2639 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.diainicio; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.diainicio IS 'Día de inicio para intérvalos mensuales';


--
-- TOC entry 2640 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.activa; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.activa IS 'Indica si la tarea está activa o no. 0-Activa, 1-Inactiva';


--
-- TOC entry 2641 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.paramvalues; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.paramvalues IS 'Valores de los parámetros';


--
-- TOC entry 2642 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.intervalo; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.intervalo IS 'Repetición por hora';


--
-- TOC entry 2643 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.paramnames; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.paramnames IS 'Nombre de los parámetros del reporte';


--
-- TOC entry 2644 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.ruta_salida; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.ruta_salida IS 'Ruta URL de salida de reporte';


--
-- TOC entry 2645 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.opctareas; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.opctareas IS 'Opciones de tareas: 1-Envía reportes a lista de correo, 2-Envía reportes a ruta, 3-Envía reportes a lista personalizada';


--
-- TOC entry 2646 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.formato; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.formato IS 'Formato de envio PDF, XLS, XLSX, ODS';


--
-- TOC entry 2647 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.instancia; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.instancia IS 'Instancia de usuario';


--
-- TOC entry 2648 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN t_programacion.dias_semana; Type: COMMENT; Schema: public; Owner: openbizview
--

COMMENT ON COLUMN t_programacion.dias_semana IS 'Dias de la semana';


--
-- TOC entry 206 (class 1259 OID 24679)
-- Name: vcount; Type: TABLE; Schema: public; Owner: openbizview
--

CREATE TABLE vcount (
    count bigint
);


ALTER TABLE vcount OWNER TO openbizview;

--
-- TOC entry 2191 (class 2606 OID 17004)
-- Name: acccat1_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_pk PRIMARY KEY (b_codrol, b_codcat1, instancia);


--
-- TOC entry 2193 (class 2606 OID 17006)
-- Name: acccat2_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, instancia);


--
-- TOC entry 2195 (class 2606 OID 17008)
-- Name: acccat3_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, b_codcat3, instancia);


--
-- TOC entry 2197 (class 2606 OID 17010)
-- Name: acccat4_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, b_codcat3, b_codcat4, instancia);


--
-- TOC entry 2200 (class 2606 OID 17012)
-- Name: bvt001_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt001
    ADD CONSTRAINT bvt001_pk PRIMARY KEY (codrep, instancia);


--
-- TOC entry 2202 (class 2606 OID 17014)
-- Name: bvt001a_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt001a
    ADD CONSTRAINT bvt001a_pk PRIMARY KEY (codgrup, instancia);


--
-- TOC entry 2204 (class 2606 OID 17016)
-- Name: bvt002_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt002
    ADD CONSTRAINT bvt002_pk PRIMARY KEY (coduser);


--
-- TOC entry 2206 (class 2606 OID 17018)
-- Name: bvt003_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt003
    ADD CONSTRAINT bvt003_pk PRIMARY KEY (codrol, instancia);


--
-- TOC entry 2208 (class 2606 OID 17020)
-- Name: bvt005_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_pk PRIMARY KEY (b_codrol, codopc, instancia);


--
-- TOC entry 2211 (class 2606 OID 17022)
-- Name: bvt006_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt006
    ADD CONSTRAINT bvt006_pk PRIMARY KEY (b_codrep, b_coduser, fecacc, instancia);


--
-- TOC entry 2213 (class 2606 OID 17024)
-- Name: bvt007_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_pk PRIMARY KEY (b_codrol, b_codrep, instancia);


--
-- TOC entry 2239 (class 2606 OID 24755)
-- Name: bvt008_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt008
    ADD CONSTRAINT bvt008_pk PRIMARY KEY (coduser, b_codrol, instancia);


--
-- TOC entry 2215 (class 2606 OID 17026)
-- Name: bvtcat1_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat1
    ADD CONSTRAINT bvtcat1_pk PRIMARY KEY (codcat1, instancia);


--
-- TOC entry 2217 (class 2606 OID 17028)
-- Name: bvtcat2_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat2
    ADD CONSTRAINT bvtcat2_pk PRIMARY KEY (b_codcat1, codcat2, instancia);


--
-- TOC entry 2219 (class 2606 OID 17030)
-- Name: bvtcat3_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat3
    ADD CONSTRAINT bvtcat3_pk PRIMARY KEY (b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2221 (class 2606 OID 17032)
-- Name: bvtcat4_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat4
    ADD CONSTRAINT bvtcat4_pk PRIMARY KEY (b_codcat1, b_codcat2, b_codcat3, codcat4, instancia);


--
-- TOC entry 2223 (class 2606 OID 17034)
-- Name: bvtmenu_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_pk PRIMARY KEY (b_codrol, codopc, instancia);


--
-- TOC entry 2225 (class 2606 OID 17036)
-- Name: bvtparams_number_temp_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtparams_number_temp
    ADD CONSTRAINT bvtparams_number_temp_pk PRIMARY KEY (codrep, sessionid);


--
-- TOC entry 2227 (class 2606 OID 17038)
-- Name: bvtparams_temp_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtparams_temp
    ADD CONSTRAINT bvtparams_temp_pk PRIMARY KEY (codrep, paramname, sessionid);


--
-- TOC entry 2229 (class 2606 OID 17040)
-- Name: instancias_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY instancias
    ADD CONSTRAINT instancias_pk PRIMARY KEY (instancia);


--
-- TOC entry 2231 (class 2606 OID 17042)
-- Name: instancias_usr_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY instancias_usr
    ADD CONSTRAINT instancias_usr_pk PRIMARY KEY (coduser, instancia);


--
-- TOC entry 2233 (class 2606 OID 17044)
-- Name: mailgrupos_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY mailgrupos
    ADD CONSTRAINT mailgrupos_pk PRIMARY KEY (idgrupo, instancia);


--
-- TOC entry 2235 (class 2606 OID 17046)
-- Name: maillista_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_pk PRIMARY KEY (idgrupo, idmail, instancia);


--
-- TOC entry 2237 (class 2606 OID 17048)
-- Name: t_programacion_pk; Type: CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_pk PRIMARY KEY (disparador, job, instancia);


--
-- TOC entry 2198 (class 1259 OID 24704)
-- Name: bvt001_index1; Type: INDEX; Schema: public; Owner: openbizview
--

CREATE INDEX bvt001_index1 ON bvt001 USING btree (instancia);


--
-- TOC entry 2209 (class 1259 OID 24705)
-- Name: bvt006_index1; Type: INDEX; Schema: public; Owner: openbizview
--

CREATE INDEX bvt006_index1 ON bvt006 USING btree (b_coduser, instancia);


--
-- TOC entry 2240 (class 2606 OID 17049)
-- Name: acccat1_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_cat_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2241 (class 2606 OID 17054)
-- Name: acccat1_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2242 (class 2606 OID 17059)
-- Name: acccat2_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2243 (class 2606 OID 17064)
-- Name: acccat2_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2244 (class 2606 OID 17069)
-- Name: acccat2_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2245 (class 2606 OID 17074)
-- Name: acccat3_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2246 (class 2606 OID 17079)
-- Name: acccat3_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2247 (class 2606 OID 17084)
-- Name: acccat3_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, instancia) REFERENCES bvtcat3(b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2248 (class 2606 OID 17089)
-- Name: acccat3_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2249 (class 2606 OID 17094)
-- Name: acccat4_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2250 (class 2606 OID 17099)
-- Name: acccat4_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2251 (class 2606 OID 17104)
-- Name: acccat4_cat3_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat3_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, instancia) REFERENCES bvtcat3(b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2252 (class 2606 OID 17109)
-- Name: acccat4_cat4_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat4_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, b_codcat4, instancia) REFERENCES bvtcat4(b_codcat1, b_codcat2, b_codcat3, codcat4, instancia);


--
-- TOC entry 2253 (class 2606 OID 17114)
-- Name: acccat4_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2254 (class 2606 OID 17119)
-- Name: biaudit_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY biaudit
    ADD CONSTRAINT biaudit_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2255 (class 2606 OID 17124)
-- Name: bvt001_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt001
    ADD CONSTRAINT bvt001_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2256 (class 2606 OID 24602)
-- Name: bvt001_fk2; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt001
    ADD CONSTRAINT bvt001_fk2 FOREIGN KEY (codgrup, instancia) REFERENCES bvt001a(codgrup, instancia);


--
-- TOC entry 2257 (class 2606 OID 17129)
-- Name: bvt001a_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt001a
    ADD CONSTRAINT bvt001a_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2258 (class 2606 OID 24607)
-- Name: bvt002_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt002
    ADD CONSTRAINT bvt002_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2259 (class 2606 OID 24617)
-- Name: bvt002_fk2; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt002
    ADD CONSTRAINT bvt002_fk2 FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2260 (class 2606 OID 17134)
-- Name: bvt003_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt003
    ADD CONSTRAINT bvt003_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2261 (class 2606 OID 17139)
-- Name: bvt005_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2262 (class 2606 OID 17144)
-- Name: bvt005_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2263 (class 2606 OID 17149)
-- Name: bvt006_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt006
    ADD CONSTRAINT bvt006_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2264 (class 2606 OID 17154)
-- Name: bvt007_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2265 (class 2606 OID 17159)
-- Name: bvt007_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2279 (class 2606 OID 24739)
-- Name: bvt008_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt008
    ADD CONSTRAINT bvt008_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2281 (class 2606 OID 24756)
-- Name: bvt008_fk2; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt008
    ADD CONSTRAINT bvt008_fk2 FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2280 (class 2606 OID 24749)
-- Name: bvt008_fk3; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvt008
    ADD CONSTRAINT bvt008_fk3 FOREIGN KEY (coduser) REFERENCES bvt002(coduser);


--
-- TOC entry 2266 (class 2606 OID 17164)
-- Name: bvtcat1_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat1
    ADD CONSTRAINT bvtcat1_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2267 (class 2606 OID 17169)
-- Name: bvtcat2_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat2
    ADD CONSTRAINT bvtcat2_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2268 (class 2606 OID 17174)
-- Name: bvtcat3_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat3
    ADD CONSTRAINT bvtcat3_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2269 (class 2606 OID 17179)
-- Name: bvtcat4_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtcat4
    ADD CONSTRAINT bvtcat4_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2270 (class 2606 OID 17184)
-- Name: bvtmenu_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2271 (class 2606 OID 17189)
-- Name: bvtmenu_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2272 (class 2606 OID 17194)
-- Name: instancias_usr_fk2; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY instancias_usr
    ADD CONSTRAINT instancias_usr_fk2 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2273 (class 2606 OID 17199)
-- Name: mailgrupos_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY mailgrupos
    ADD CONSTRAINT mailgrupos_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2274 (class 2606 OID 17204)
-- Name: maillista_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2275 (class 2606 OID 17209)
-- Name: maillista_idgrupo_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_idgrupo_fk FOREIGN KEY (idgrupo, instancia) REFERENCES mailgrupos(idgrupo, instancia);


--
-- TOC entry 2276 (class 2606 OID 17214)
-- Name: t_programacion_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2277 (class 2606 OID 17219)
-- Name: t_programacion_idgrupo_fk1; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_idgrupo_fk1 FOREIGN KEY (idgrupo, instancia) REFERENCES mailgrupos(idgrupo, instancia);


--
-- TOC entry 2278 (class 2606 OID 24692)
-- Name: t_programacion_rep_fk; Type: FK CONSTRAINT; Schema: public; Owner: openbizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_rep_fk FOREIGN KEY (codrep, instancia) REFERENCES bvt001(codrep, instancia);


--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: openbizview
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM openbizview;
GRANT ALL ON SCHEMA public TO openbizview;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-04-30 23:22:35 BOT

--
-- PostgreSQL database dump complete
--

