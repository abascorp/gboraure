--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.0
-- Dumped by pg_dump version 9.4.0
-- Started on 2014-12-26 11:45:42 VET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 197 (class 3079 OID 11935)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 197
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 232 (class 1255 OID 18017)
-- Name: count_acccat1(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_acccat1(pbusqueda character, pcodrol character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 232
-- Name: FUNCTION count_acccat1(pbusqueda character, pcodrol character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_acccat1(pbusqueda character, pcodrol character, pinstancia character) IS 'Cuenta registros de acceso a categoria1';


--
-- TOC entry 233 (class 1255 OID 18018)
-- Name: count_acccat2(character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 233
-- Name: FUNCTION count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_acccat2(pbusqueda character, pcodrol character, pcodcat1 character, pinstancia character) IS 'Cuenta registros de acceso a categoria2';


--
-- TOC entry 234 (class 1255 OID 18019)
-- Name: count_acccat3(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 234
-- Name: FUNCTION count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_acccat3(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pinstancia character) IS 'Cuenta registros de acceso a categoria3';


--
-- TOC entry 210 (class 1255 OID 16691)
-- Name: count_acccat4(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character) OWNER TO bizview;

--
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 210
-- Name: FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character) IS 'Cuenta registros de acceso a categoria4';


--
-- TOC entry 235 (class 1255 OID 18020)
-- Name: count_acccat4(character, character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 235
-- Name: FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_acccat4(pbusqueda character, pcodrol character, pcodcat1 character, pcodcat2 character, pcodcat3 character, pinstancia character) IS 'Cuenta registros de acceso a categoria4';


--
-- TOC entry 211 (class 1255 OID 16692)
-- Name: count_biaudit(character, date, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 211
-- Name: FUNCTION count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_biaudit(pbusqueda character, pfecha date, pvalor character, pinstancia character) IS 'Cuenta registros de auditoria';


--
-- TOC entry 212 (class 1255 OID 16693)
-- Name: count_bvt001(character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 212
-- Name: FUNCTION count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt001(pbusqueda character, pcodrol character, pgrupo character, pinstancia character) IS 'Cuenta registros de reportes';


--
-- TOC entry 213 (class 1255 OID 16694)
-- Name: count_bvt001a(character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt001a(pbusqueda character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2296 (class 0 OID 0)
-- Dependencies: 213
-- Name: FUNCTION count_bvt001a(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt001a(pbusqueda character, pinstancia character) IS 'Cuenta registros de reportes';


--
-- TOC entry 214 (class 1255 OID 16695)
-- Name: count_bvt002(character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt002(pbusqueda character) OWNER TO bizview;

--
-- TOC entry 2297 (class 0 OID 0)
-- Dependencies: 214
-- Name: FUNCTION count_bvt002(pbusqueda character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt002(pbusqueda character) IS 'Cuenta registros de usuario';


--
-- TOC entry 222 (class 1255 OID 17815)
-- Name: count_bvt003(character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt003(pbusqueda character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 222
-- Name: FUNCTION count_bvt003(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt003(pbusqueda character, pinstancia character) IS 'Cuenta registros de roles';


--
-- TOC entry 225 (class 1255 OID 17823)
-- Name: count_bvt003a(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
--

CREATE FUNCTION count_bvt003a(pbusqueda character, pusuario character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
from bvt003a a, bvt003 b
where a.codrol = b.codrol
and a.coduser||b.desrol  like '%'||pbusqueda||'%'
and a.coduser like pusuario||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt003a(pbusqueda character, pusuario character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2299 (class 0 OID 0)
-- Dependencies: 225
-- Name: FUNCTION count_bvt003a(pbusqueda character, pusuario character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt003a(pbusqueda character, pusuario character, pinstancia character) IS 'Cuenta registros de roles adicionales';


--
-- TOC entry 215 (class 1255 OID 16698)
-- Name: count_bvt005(character, character); Type: FUNCTION; Schema: public; Owner: bizview
--

CREATE FUNCTION count_bvt005(pbusqueda character, prol character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM Bvt005
WHERE b_codrol||codopc||desopc like  '%'||pbusqueda||'%'
and b_codrol like prol||'%';
return vcount;
end;$$;


ALTER FUNCTION public.count_bvt005(pbusqueda character, prol character) OWNER TO bizview;

--
-- TOC entry 2300 (class 0 OID 0)
-- Dependencies: 215
-- Name: FUNCTION count_bvt005(pbusqueda character, prol character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt005(pbusqueda character, prol character) IS 'Cuenta registros de acceso a botones';


--
-- TOC entry 228 (class 1255 OID 17850)
-- Name: count_bvt005(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt005(pbusqueda character, prol character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2301 (class 0 OID 0)
-- Dependencies: 228
-- Name: FUNCTION count_bvt005(pbusqueda character, prol character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt005(pbusqueda character, prol character, pinstancia character) IS 'Cuenta registros de acceso a botones';


--
-- TOC entry 216 (class 1255 OID 16699)
-- Name: count_bvt006(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt006(pbusqueda character, pcoduser character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2302 (class 0 OID 0)
-- Dependencies: 216
-- Name: FUNCTION count_bvt006(pbusqueda character, pcoduser character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt006(pbusqueda character, pcoduser character, pinstancia character) IS 'Cuenta registros de auditoría';


--
-- TOC entry 217 (class 1255 OID 16700)
-- Name: count_bvt007(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvt007(pbusqueda character, prol character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2303 (class 0 OID 0)
-- Dependencies: 217
-- Name: FUNCTION count_bvt007(pbusqueda character, prol character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvt007(pbusqueda character, prol character, pinstancia character) IS 'Cuenta registros de acceso a reportes';


--
-- TOC entry 229 (class 1255 OID 17921)
-- Name: count_bvtcat1(character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvtcat1(pbusqueda character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2304 (class 0 OID 0)
-- Dependencies: 229
-- Name: FUNCTION count_bvtcat1(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvtcat1(pbusqueda character, pinstancia character) IS 'Cuenta registros de categoria1';


--
-- TOC entry 227 (class 1255 OID 17922)
-- Name: count_bvtcat2(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2305 (class 0 OID 0)
-- Dependencies: 227
-- Name: FUNCTION count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvtcat2(pbusqueda character, pbcodcat1 character, pinstancia character) IS 'Cuenta registros de auditoria de reporte impresos';


--
-- TOC entry 230 (class 1255 OID 17924)
-- Name: count_bvtcat3(character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 230
-- Name: FUNCTION count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvtcat3(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pinstancia character) IS 'Cuenta registros de categoria 3';


--
-- TOC entry 231 (class 1255 OID 17925)
-- Name: count_bvtcat4(character, character, character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 231
-- Name: FUNCTION count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvtcat4(pbusqueda character, pbcodcat1 character, pbcodcat2 character, pbcodcat3 character, pinstancia character) IS 'Cuenta registros de categoria 4';


--
-- TOC entry 226 (class 1255 OID 17837)
-- Name: count_bvtmenu(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 226
-- Name: FUNCTION count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_bvtmenu(pbusqueda character, pcodrol character, pinstancia character) IS 'Cuenta registros de opciones roles';


--
-- TOC entry 218 (class 1255 OID 16706)
-- Name: count_instancias(character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_instancias(pbusqueda character) OWNER TO bizview;

--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 218
-- Name: FUNCTION count_instancias(pbusqueda character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_instancias(pbusqueda character) IS 'Cuenta registros de instancias';


--
-- TOC entry 219 (class 1255 OID 16707)
-- Name: count_instanciasusr(character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_instanciasusr(pbusqueda character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 219
-- Name: FUNCTION count_instanciasusr(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_instanciasusr(pbusqueda character, pinstancia character) IS 'Cuenta registros de instancias usuarios';


--
-- TOC entry 220 (class 1255 OID 16708)
-- Name: count_mailgrupo(character, character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.count_mailgrupo(pbusqueda character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 220
-- Name: FUNCTION count_mailgrupo(pbusqueda character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_mailgrupo(pbusqueda character, pinstancia character) IS 'Cuenta registros de mailgrupo';


--
-- TOC entry 223 (class 1255 OID 17799)
-- Name: count_maillista(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
--

CREATE FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  MAILGRUPOS A, MAILLISTA B
WHERE A.IDGRUPO=B.IDGRUPO
and cast(a.idgrupo as text)||cast(b.idmail as text)||b.mail like  '%'||pbusqueda||'%'
and  cast(a.idgrupo as text) like pidgrupo||'%'
and cast(b.instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_maillista(pbusqueda character, pidgrupo character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 223
-- Name: FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_maillista(pbusqueda character, pidgrupo character, pinstancia character) IS 'Cuenta registros de lista de correos';


--
-- TOC entry 224 (class 1255 OID 17807)
-- Name: count_programacion(character, character, character); Type: FUNCTION; Schema: public; Owner: bizview
--

CREATE FUNCTION count_programacion(pbusqueda character, popctareas character, pinstancia character) RETURNS bigint
    LANGUAGE plpgsql
    AS $$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||disparador||codrep like  '%'||pbusqueda||'%'
and opctareas like popctareas||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$$;


ALTER FUNCTION public.count_programacion(pbusqueda character, popctareas character, pinstancia character) OWNER TO bizview;

--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 224
-- Name: FUNCTION count_programacion(pbusqueda character, popctareas character, pinstancia character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION count_programacion(pbusqueda character, popctareas character, pinstancia character) IS 'Cuenta registros de programación';


--
-- TOC entry 221 (class 1255 OID 16711)
-- Name: fn_desgrup(character); Type: FUNCTION; Schema: public; Owner: bizview
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


ALTER FUNCTION public.fn_desgrup(pcodgrup character) OWNER TO bizview;

--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 221
-- Name: FUNCTION fn_desgrup(pcodgrup character); Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON FUNCTION fn_desgrup(pcodgrup character) IS 'Retorna la descripción del grupo de reportes';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 16712)
-- Name: acccat1; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE acccat1 OWNER TO bizview;

--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 172
-- Name: TABLE acccat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE acccat1 IS 'Acceso a Categoria1';


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.b_codrol IS 'Código del rol';


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN acccat1.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat1.instancia IS 'Instancia de usuario';


--
-- TOC entry 173 (class 1259 OID 16715)
-- Name: acccat2; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE acccat2 OWNER TO bizview;

--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 173
-- Name: TABLE acccat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE acccat2 IS 'Acceso a Categoria2';


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.b_codrol IS 'Código del rol';


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.b_codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN acccat2.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat2.instancia IS 'Instancia de usuario';


--
-- TOC entry 174 (class 1259 OID 16718)
-- Name: acccat3; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE acccat3 OWNER TO bizview;

--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 174
-- Name: TABLE acccat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE acccat3 IS 'Acceso a Categoria3';


--
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.b_codrol IS 'Código del rol';


--
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.b_codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.b_codcat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.b_codcat3 IS 'Código categoria3';


--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN acccat3.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat3.instancia IS 'Instancia de usuario';


--
-- TOC entry 175 (class 1259 OID 16721)
-- Name: acccat4; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE acccat4 OWNER TO bizview;

--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 175
-- Name: TABLE acccat4; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE acccat4 IS 'Acceso a Categoria4';


--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.b_codrol IS 'Código del rol';


--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.b_codcat1 IS 'Código categoria1';


--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.b_codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.b_codcat2 IS 'Código categoria2';


--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.b_codcat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.b_codcat3 IS 'Código categoria3';


--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.b_codcat4; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.b_codcat4 IS 'Código categoria4';


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN acccat4.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN acccat4.instancia IS 'Instancia de usuario';


--
-- TOC entry 176 (class 1259 OID 16724)
-- Name: biaudit; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE biaudit (
    fecacc character(30) NOT NULL,
    detfaz character(500) NOT NULL,
    result character(20) NOT NULL,
    negocio character(20),
    fechadia date NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE biaudit OWNER TO bizview;

--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 176
-- Name: TABLE biaudit; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE biaudit IS 'Detalle del log de interfaces';


--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.fecacc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.fecacc IS 'Fecha de la interfaz';


--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.detfaz; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.detfaz IS 'Descripción de la interfaz';


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.result; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.result IS 'Resultado de la interfaz';


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.negocio; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.negocio IS 'Negocio con el que se realiza la interfaz';


--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.fechadia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.fechadia IS 'Fecha de la interfaz';


--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN biaudit.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN biaudit.instancia IS 'Instancia de usuarios';


--
-- TOC entry 177 (class 1259 OID 16730)
-- Name: bvt001; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE bvt001 (
    codrep character(15) NOT NULL,
    desrep character(50) NOT NULL,
    comrep character(50) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    codgrup character(10),
    instancia integer NOT NULL
);


ALTER TABLE bvt001 OWNER TO bizview;

--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 177
-- Name: TABLE bvt001; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt001 IS 'Agrupación de reportes';


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.codrep IS 'Código de reporte';


--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.desrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.desrep IS 'Descripción de reporte';


--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.comrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.comrep IS 'Comentario de reporte';


--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2366 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.usract IS 'Usuario de creación de registro';


--
-- TOC entry 2367 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2368 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.codgrup; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.codgrup IS 'Código de grupo';


--
-- TOC entry 2369 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN bvt001.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001.instancia IS 'Instancia';


--
-- TOC entry 178 (class 1259 OID 16733)
-- Name: bvt001a; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvt001a OWNER TO bizview;

--
-- TOC entry 2370 (class 0 OID 0)
-- Dependencies: 178
-- Name: TABLE bvt001a; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt001a IS 'Agrupación de reportes';


--
-- TOC entry 2371 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.codgrup; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.codgrup IS 'Código del grupo';


--
-- TOC entry 2372 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.desgrup; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.desgrup IS 'Descripción del grupo';


--
-- TOC entry 2373 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2374 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.feccre IS 'Fecha de creación de registros';


--
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.usract IS 'Usuario de actualización de registros';


--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.fecact IS 'Fecha de actualización de registros';


--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN bvt001a.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt001a.instancia IS 'Instancia usuarios';


--
-- TOC entry 179 (class 1259 OID 16736)
-- Name: bvt002; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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
    instancia integer DEFAULT 0
);


ALTER TABLE bvt002 OWNER TO bizview;

--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 179
-- Name: TABLE bvt002; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt002 IS 'Usuarios';


--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.coduser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.coduser IS 'Usuario';


--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.desuser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.desuser IS 'Descripción de usuario';


--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.cluser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.cluser IS 'Clave de usuario';


--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.b_codrol IS 'Rol de usuario';


--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.mail; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.mail IS 'Dirección de correo electrónico';


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN bvt002.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt002.instancia IS 'Instancia asociada al usuario';


--
-- TOC entry 180 (class 1259 OID 16740)
-- Name: bvt003; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvt003 OWNER TO bizview;

--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 180
-- Name: TABLE bvt003; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt003 IS 'Roles';


--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.codrol IS 'Código de rol';


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.desrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.desrol IS 'Descripción del rol';


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN bvt003.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003.instancia IS 'Instancia de usuario';


--
-- TOC entry 181 (class 1259 OID 16743)
-- Name: bvt003a; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE bvt003a (
    codrol character(5) NOT NULL,
    coduser character(10) NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date,
    instancia integer NOT NULL
);


ALTER TABLE bvt003a OWNER TO bizview;

--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 181
-- Name: TABLE bvt003a; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt003a IS 'Roles adicionales usuarios';


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.codrol IS 'Código de rol';


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.coduser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.coduser IS 'Código del usuario';


--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN bvt003a.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt003a.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 182 (class 1259 OID 16746)
-- Name: bvt005; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvt005 OWNER TO bizview;

--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 182
-- Name: TABLE bvt005; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt005 IS 'Acceso a Botónes';


--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.b_codrol IS 'Código del rol';


--
-- TOC entry 2406 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.codopc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.codopc IS 'Número de opción';


--
-- TOC entry 2407 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.desopc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.desopc IS 'Descripción de opción';


--
-- TOC entry 2408 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.codvis; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.codvis IS 'Visualiza 1 - No visualiza 0';


--
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2410 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2411 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2412 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2413 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN bvt005.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt005.instancia IS 'Grupo de usuario';


--
-- TOC entry 183 (class 1259 OID 16749)
-- Name: bvt006; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE bvt006 (
    b_codrep character(15) NOT NULL,
    b_desrep character(50) NOT NULL,
    b_coduser character(100) NOT NULL,
    fecacc timestamp(6) without time zone NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE bvt006 OWNER TO bizview;

--
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 183
-- Name: TABLE bvt006; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt006 IS 'Log de reportes impresos';


--
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN bvt006.b_codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt006.b_codrep IS 'Código de reporte';


--
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN bvt006.b_desrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt006.b_desrep IS 'Descripción de reporte';


--
-- TOC entry 2417 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN bvt006.b_coduser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt006.b_coduser IS 'Usuario que imprime reporte';


--
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN bvt006.fecacc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt006.fecacc IS 'Fecha de registro';


--
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN bvt006.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt006.instancia IS 'Instancia de usuario';


--
-- TOC entry 184 (class 1259 OID 16752)
-- Name: bvt007; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvt007 OWNER TO bizview;

--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 184
-- Name: TABLE bvt007; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvt007 IS 'Acceso a Reportes';


--
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.b_codrol IS 'Código del rol';


--
-- TOC entry 2422 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.b_codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.b_codrep IS 'Código de reporte';


--
-- TOC entry 2423 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2424 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2425 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2426 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2427 (class 0 OID 0)
-- Dependencies: 184
-- Name: COLUMN bvt007.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvt007.instancia IS 'Instancia usuarios';


--
-- TOC entry 185 (class 1259 OID 16755)
-- Name: bvtcat1; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvtcat1 OWNER TO bizview;

--
-- TOC entry 2428 (class 0 OID 0)
-- Dependencies: 185
-- Name: TABLE bvtcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtcat1 IS 'Categoria1';


--
-- TOC entry 2429 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2430 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.descat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.descat1 IS 'Decripción de categoria1';


--
-- TOC entry 2431 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2432 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2433 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2434 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2435 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN bvtcat1.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat1.instancia IS 'Instancias';


--
-- TOC entry 186 (class 1259 OID 16758)
-- Name: bvtcat2; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvtcat2 OWNER TO bizview;

--
-- TOC entry 2436 (class 0 OID 0)
-- Dependencies: 186
-- Name: TABLE bvtcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtcat2 IS 'Categoria2';


--
-- TOC entry 2437 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2438 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.codcat2 IS 'Código categoria2';


--
-- TOC entry 2439 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.descat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.descat2 IS 'Descripción de categoria 2';


--
-- TOC entry 2440 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.usrcre IS 'Usuario de cración de registro';


--
-- TOC entry 2441 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2442 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2443 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2444 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN bvtcat2.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat2.instancia IS 'Instancia de usuario';


--
-- TOC entry 187 (class 1259 OID 16761)
-- Name: bvtcat3; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvtcat3 OWNER TO bizview;

--
-- TOC entry 2445 (class 0 OID 0)
-- Dependencies: 187
-- Name: TABLE bvtcat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtcat3 IS 'Categoria3';


--
-- TOC entry 2446 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2447 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.b_codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.b_codcat2 IS 'Código de categoria 2';


--
-- TOC entry 2448 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.codcat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.codcat3 IS 'Código de categoria 3';


--
-- TOC entry 2449 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.descat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.descat3 IS 'Decripción de categoria2';


--
-- TOC entry 2450 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2451 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2452 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2453 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2454 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN bvtcat3.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat3.instancia IS 'Instancia de usuario';


--
-- TOC entry 188 (class 1259 OID 16764)
-- Name: bvtcat4; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvtcat4 OWNER TO bizview;

--
-- TOC entry 2455 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE bvtcat4; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtcat4 IS 'Categoria4';


--
-- TOC entry 2456 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.b_codcat1; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.b_codcat1 IS 'Código de categoria 1';


--
-- TOC entry 2457 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.b_codcat2; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.b_codcat2 IS 'Código de categoria 2';


--
-- TOC entry 2458 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.b_codcat3; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.b_codcat3 IS 'Código de categoria 3';


--
-- TOC entry 2459 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2460 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2461 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2462 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2463 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.equicat4; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.equicat4 IS 'Equivalencia de categoria 4';


--
-- TOC entry 2464 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN bvtcat4.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtcat4.instancia IS 'Instancia de usuario';


--
-- TOC entry 189 (class 1259 OID 16767)
-- Name: bvtmenu; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE bvtmenu OWNER TO bizview;

--
-- TOC entry 2465 (class 0 OID 0)
-- Dependencies: 189
-- Name: TABLE bvtmenu; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtmenu IS 'Menú';


--
-- TOC entry 2466 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.b_codrol; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.b_codrol IS 'Usuario acceso al menú';


--
-- TOC entry 2467 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.codopc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.codopc IS 'Número de opción';


--
-- TOC entry 2468 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.desopc; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.desopc IS 'Descripción de opción';


--
-- TOC entry 2469 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.codvis; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.codvis IS '1-Visualiza, 0-No visualiza';


--
-- TOC entry 2470 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2471 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2472 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2473 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 2474 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN bvtmenu.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtmenu.instancia IS 'Instancia usuario';


--
-- TOC entry 190 (class 1259 OID 16770)
-- Name: bvtparams_number_temp; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE bvtparams_number_temp (
    codrep character(10) NOT NULL,
    sessionid character(500) NOT NULL,
    paramnumber integer NOT NULL
);


ALTER TABLE bvtparams_number_temp OWNER TO bizview;

--
-- TOC entry 2475 (class 0 OID 0)
-- Dependencies: 190
-- Name: TABLE bvtparams_number_temp; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtparams_number_temp IS 'Temporal de parametros de reportes';


--
-- TOC entry 2476 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvtparams_number_temp.codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_number_temp.codrep IS 'Código del reporte';


--
-- TOC entry 2477 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvtparams_number_temp.sessionid; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_number_temp.sessionid IS 'Id de la sessión';


--
-- TOC entry 2478 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN bvtparams_number_temp.paramnumber; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_number_temp.paramnumber IS 'Número de parámetros';


--
-- TOC entry 191 (class 1259 OID 16776)
-- Name: bvtparams_temp; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE bvtparams_temp (
    codrep character(10) NOT NULL,
    paramname character(150) NOT NULL,
    paramtype integer NOT NULL,
    sessionid character(500) NOT NULL,
    promptext character(150) NOT NULL
);


ALTER TABLE bvtparams_temp OWNER TO bizview;

--
-- TOC entry 2479 (class 0 OID 0)
-- Dependencies: 191
-- Name: TABLE bvtparams_temp; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE bvtparams_temp IS 'Temporal de parametros de reportes';


--
-- TOC entry 2480 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvtparams_temp.codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_temp.codrep IS 'Código del reporte';


--
-- TOC entry 2481 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvtparams_temp.paramname; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_temp.paramname IS 'Nombre del parámetro';


--
-- TOC entry 2482 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvtparams_temp.paramtype; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_temp.paramtype IS 'Tipo de parámetro';


--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvtparams_temp.sessionid; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_temp.sessionid IS 'Id de la sessión';


--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN bvtparams_temp.promptext; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN bvtparams_temp.promptext IS 'Descripción del parámetro';


--
-- TOC entry 192 (class 1259 OID 16782)
-- Name: instancias; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
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


ALTER TABLE instancias OWNER TO bizview;

--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 192
-- Name: TABLE instancias; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE instancias IS 'Instancias';


--
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.instancia IS 'Código';


--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.descripcion; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.descripcion IS 'Descripción';


--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN instancias.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 193 (class 1259 OID 16786)
-- Name: instancias_usr; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE instancias_usr (
    coduser character(10) NOT NULL,
    instancia integer NOT NULL,
    usrcre character(10),
    feccre date,
    usract character(10),
    fecact date
);


ALTER TABLE instancias_usr OWNER TO bizview;

--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 193
-- Name: TABLE instancias_usr; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE instancias_usr IS 'Asociar instancias a usuarios';


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.coduser; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.coduser IS 'Código de usuario';


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.instancia IS 'Instancias';


--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.usrcre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.usrcre IS 'Usuario de creación de registro';


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.feccre; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.feccre IS 'Fecha de creación de registro';


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.usract; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.usract IS 'Usuario de actualización de registro';


--
-- TOC entry 2498 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN instancias_usr.fecact; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN instancias_usr.fecact IS 'Fecha de actualización de registro';


--
-- TOC entry 194 (class 1259 OID 16789)
-- Name: mailgrupos; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE mailgrupos (
    idgrupo integer NOT NULL,
    desgrupo character(50) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE mailgrupos OWNER TO bizview;

--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE mailgrupos; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE mailgrupos IS 'Grupos de correo';


--
-- TOC entry 2500 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN mailgrupos.idgrupo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN mailgrupos.idgrupo IS 'Identificador de grupo';


--
-- TOC entry 2501 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN mailgrupos.desgrupo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN mailgrupos.desgrupo IS 'Descripción del grupo';


--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN mailgrupos.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN mailgrupos.instancia IS 'Instancia usuario';


--
-- TOC entry 195 (class 1259 OID 16792)
-- Name: maillista; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE maillista (
    idgrupo integer NOT NULL,
    idmail character(50) NOT NULL,
    mail character(100) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE maillista OWNER TO bizview;

--
-- TOC entry 2503 (class 0 OID 0)
-- Dependencies: 195
-- Name: TABLE maillista; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE maillista IS 'Listas de correos';


--
-- TOC entry 2504 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN maillista.idgrupo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN maillista.idgrupo IS 'Grupo de correos';


--
-- TOC entry 2505 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN maillista.idmail; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN maillista.idmail IS 'Id de correo';


--
-- TOC entry 2506 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN maillista.mail; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN maillista.mail IS 'Correo electrónico';


--
-- TOC entry 2507 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN maillista.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN maillista.instancia IS 'Insttancia de usuario';


--
-- TOC entry 196 (class 1259 OID 16795)
-- Name: t_programacion; Type: TABLE; Schema: public; Owner: bizview; Tablespace: 
--

CREATE TABLE t_programacion (
    disparador character(20) NOT NULL,
    grupo character(20) NOT NULL,
    diasem integer NOT NULL,
    hora character(5) NOT NULL,
    frecuencia character(1),
    asunto character(50) NOT NULL,
    contenido character(4000) NOT NULL,
    codrep character(10) NOT NULL,
    rutarep character(1500) NOT NULL,
    rutatemp character(1500) NOT NULL,
    idgrupo integer NOT NULL,
    job character(20) NOT NULL,
    diames character(2) NOT NULL,
    diainicio date NOT NULL,
    activa character(1) NOT NULL,
    paramvalues character(500),
    intervalo integer NOT NULL,
    paramnames character(500),
    ruta_salida character(500),
    opctareas character(1) NOT NULL,
    formato character(4) NOT NULL,
    instancia integer NOT NULL
);


ALTER TABLE t_programacion OWNER TO bizview;

--
-- TOC entry 2508 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE t_programacion; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON TABLE t_programacion IS 'Programación de envio de correos';


--
-- TOC entry 2509 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.disparador; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.disparador IS 'Nombre del disparador';


--
-- TOC entry 2510 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.grupo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.grupo IS 'Nombre del grupo de la tarea';


--
-- TOC entry 2511 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.diasem; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.diasem IS 'Día de la semana';


--
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.hora; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.hora IS 'Hora de ejecución hh:mm';


--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.frecuencia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.frecuencia IS 'Frecuencia de ejecución, 0-Diaria, 1-Semanal, 2-Personalizada, 3-Mensual, 4-Bimensual, 5-Trimestral';


--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.asunto; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.asunto IS 'Asunto del correo';


--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.contenido; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.contenido IS 'Contenido del correo';


--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.codrep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.codrep IS 'Código de reporte a enviar';


--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.rutarep; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.rutarep IS 'Ruta de ubicación del reporte';


--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.rutatemp; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.rutatemp IS 'Ruta de generación temporal del reporte';


--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.idgrupo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.idgrupo IS 'Id grupo de lista de correos';


--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.job; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.job IS 'Nombre de la tarea o job';


--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.diames; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.diames IS 'Día del mes para envío personalizados';


--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.diainicio; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.diainicio IS 'Día de inicio para intérvalos mensuales';


--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.activa; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.activa IS 'Indica si la tarea está activa o no. 0-Activa, 1-Inactiva';


--
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.paramvalues; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.paramvalues IS 'Valores de los parámetros';


--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.intervalo; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.intervalo IS 'Repetición por hora';


--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.paramnames; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.paramnames IS 'Nombre de los parámetros del reporte';


--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.ruta_salida; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.ruta_salida IS 'Ruta URL de salida de reporte';


--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.opctareas; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.opctareas IS 'Opciones de tareas: 1-Envía reportes a lista de correo, 2-Envía reportes a ruta, 3-Envía reportes a lista personalizada';


--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.formato; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.formato IS 'Formato de envio PDF, XLS, XLSX, ODS';


--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN t_programacion.instancia; Type: COMMENT; Schema: public; Owner: bizview
--

COMMENT ON COLUMN t_programacion.instancia IS 'Instancia de usuario';


--
-- TOC entry 2086 (class 2606 OID 17934)
-- Name: acccat1_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_pk PRIMARY KEY (b_codrol, b_codcat1, instancia);


--
-- TOC entry 2088 (class 2606 OID 17936)
-- Name: acccat2_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, instancia);


--
-- TOC entry 2090 (class 2606 OID 17938)
-- Name: acccat3_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, b_codcat3, instancia);


--
-- TOC entry 2092 (class 2606 OID 17940)
-- Name: acccat4_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_pk PRIMARY KEY (b_codrol, b_codcat1, b_codcat2, b_codcat3, b_codcat4, instancia);


--
-- TOC entry 2094 (class 2606 OID 16810)
-- Name: bvt001_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt001
    ADD CONSTRAINT bvt001_pk PRIMARY KEY (codrep, instancia);


--
-- TOC entry 2096 (class 2606 OID 16812)
-- Name: bvt001a_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt001a
    ADD CONSTRAINT bvt001a_pk PRIMARY KEY (codgrup, instancia);


--
-- TOC entry 2098 (class 2606 OID 17857)
-- Name: bvt002_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt002
    ADD CONSTRAINT bvt002_pk PRIMARY KEY (coduser);


--
-- TOC entry 2100 (class 2606 OID 17809)
-- Name: bvt003_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt003
    ADD CONSTRAINT bvt003_pk PRIMARY KEY (codrol, instancia);


--
-- TOC entry 2102 (class 2606 OID 17817)
-- Name: bvt003a_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt003a
    ADD CONSTRAINT bvt003a_pk PRIMARY KEY (coduser, codrol, instancia);


--
-- TOC entry 2104 (class 2606 OID 17839)
-- Name: bvt005_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_pk PRIMARY KEY (b_codrol, codopc, instancia);


--
-- TOC entry 2106 (class 2606 OID 16822)
-- Name: bvt006_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt006
    ADD CONSTRAINT bvt006_pk PRIMARY KEY (b_codrep, b_coduser, fecacc, instancia);


--
-- TOC entry 2108 (class 2606 OID 16824)
-- Name: bvt007_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_pk PRIMARY KEY (b_codrol, b_codrep, instancia);


--
-- TOC entry 2110 (class 2606 OID 17899)
-- Name: bvtcat1_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtcat1
    ADD CONSTRAINT bvtcat1_pk PRIMARY KEY (codcat1, instancia);


--
-- TOC entry 2112 (class 2606 OID 17901)
-- Name: bvtcat2_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtcat2
    ADD CONSTRAINT bvtcat2_pk PRIMARY KEY (b_codcat1, codcat2, instancia);


--
-- TOC entry 2114 (class 2606 OID 17908)
-- Name: bvtcat3_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtcat3
    ADD CONSTRAINT bvtcat3_pk PRIMARY KEY (b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2116 (class 2606 OID 17915)
-- Name: bvtcat4_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtcat4
    ADD CONSTRAINT bvtcat4_pk PRIMARY KEY (b_codcat1, b_codcat2, b_codcat3, codcat4, instancia);


--
-- TOC entry 2118 (class 2606 OID 17825)
-- Name: bvtmenu_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_pk PRIMARY KEY (b_codrol, codopc, instancia);


--
-- TOC entry 2120 (class 2606 OID 16836)
-- Name: bvtparams_number_temp_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtparams_number_temp
    ADD CONSTRAINT bvtparams_number_temp_pk PRIMARY KEY (codrep, sessionid);


--
-- TOC entry 2122 (class 2606 OID 16838)
-- Name: bvtparams_temp_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY bvtparams_temp
    ADD CONSTRAINT bvtparams_temp_pk PRIMARY KEY (codrep, paramname, sessionid);


--
-- TOC entry 2124 (class 2606 OID 16840)
-- Name: instancias_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY instancias
    ADD CONSTRAINT instancias_pk PRIMARY KEY (instancia);


--
-- TOC entry 2126 (class 2606 OID 16842)
-- Name: instancias_usr_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY instancias_usr
    ADD CONSTRAINT instancias_usr_pk PRIMARY KEY (coduser, instancia);


--
-- TOC entry 2128 (class 2606 OID 16844)
-- Name: mailgrupos_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY mailgrupos
    ADD CONSTRAINT mailgrupos_pk PRIMARY KEY (idgrupo, instancia);


--
-- TOC entry 2130 (class 2606 OID 17792)
-- Name: maillista_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_pk PRIMARY KEY (idgrupo, idmail, instancia);


--
-- TOC entry 2132 (class 2606 OID 17801)
-- Name: t_programacion_pk; Type: CONSTRAINT; Schema: public; Owner: bizview; Tablespace: 
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_pk PRIMARY KEY (disparador, job, instancia);


--
-- TOC entry 2133 (class 2606 OID 17941)
-- Name: acccat1_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_cat_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2134 (class 2606 OID 17946)
-- Name: acccat1_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat1
    ADD CONSTRAINT acccat1_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2137 (class 2606 OID 17966)
-- Name: acccat2_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2135 (class 2606 OID 17956)
-- Name: acccat2_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2136 (class 2606 OID 17961)
-- Name: acccat2_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat2
    ADD CONSTRAINT acccat2_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2138 (class 2606 OID 17971)
-- Name: acccat3_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2139 (class 2606 OID 17976)
-- Name: acccat3_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2140 (class 2606 OID 17981)
-- Name: acccat3_cat_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_cat_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, instancia) REFERENCES bvtcat3(b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2141 (class 2606 OID 17986)
-- Name: acccat3_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat3
    ADD CONSTRAINT acccat3_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2142 (class 2606 OID 17991)
-- Name: acccat4_cat1_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat1_fk FOREIGN KEY (b_codcat1, instancia) REFERENCES bvtcat1(codcat1, instancia);


--
-- TOC entry 2143 (class 2606 OID 17996)
-- Name: acccat4_cat2_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat2_fk FOREIGN KEY (b_codcat1, b_codcat2, instancia) REFERENCES bvtcat2(b_codcat1, codcat2, instancia);


--
-- TOC entry 2144 (class 2606 OID 18001)
-- Name: acccat4_cat3_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat3_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, instancia) REFERENCES bvtcat3(b_codcat1, b_codcat2, codcat3, instancia);


--
-- TOC entry 2145 (class 2606 OID 18006)
-- Name: acccat4_cat4_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_cat4_fk FOREIGN KEY (b_codcat1, b_codcat2, b_codcat3, b_codcat4, instancia) REFERENCES bvtcat4(b_codcat1, b_codcat2, b_codcat3, codcat4, instancia);


--
-- TOC entry 2146 (class 2606 OID 18011)
-- Name: acccat4_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY acccat4
    ADD CONSTRAINT acccat4_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2147 (class 2606 OID 16914)
-- Name: biaudit_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY biaudit
    ADD CONSTRAINT biaudit_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2148 (class 2606 OID 16919)
-- Name: bvt001_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt001
    ADD CONSTRAINT bvt001_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2149 (class 2606 OID 16924)
-- Name: bvt001a_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt001a
    ADD CONSTRAINT bvt001a_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2150 (class 2606 OID 17810)
-- Name: bvt003_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt003
    ADD CONSTRAINT bvt003_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2151 (class 2606 OID 17818)
-- Name: bvt003a_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt003a
    ADD CONSTRAINT bvt003a_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2153 (class 2606 OID 17873)
-- Name: bvt003a_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt003a
    ADD CONSTRAINT bvt003a_rol_fk FOREIGN KEY (codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2152 (class 2606 OID 17868)
-- Name: bvt003a_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt003a
    ADD CONSTRAINT bvt003a_user_fk FOREIGN KEY (coduser) REFERENCES bvt002(coduser);


--
-- TOC entry 2155 (class 2606 OID 17845)
-- Name: bvt005_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2154 (class 2606 OID 17840)
-- Name: bvt005_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt005
    ADD CONSTRAINT bvt005_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2156 (class 2606 OID 16944)
-- Name: bvt006_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt006
    ADD CONSTRAINT bvt006_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2157 (class 2606 OID 16949)
-- Name: bvt007_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2158 (class 2606 OID 17851)
-- Name: bvt007_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvt007
    ADD CONSTRAINT bvt007_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2159 (class 2606 OID 16959)
-- Name: bvtcat1_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtcat1
    ADD CONSTRAINT bvtcat1_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2160 (class 2606 OID 17902)
-- Name: bvtcat2_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtcat2
    ADD CONSTRAINT bvtcat2_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2161 (class 2606 OID 17909)
-- Name: bvtcat3_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtcat3
    ADD CONSTRAINT bvtcat3_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2162 (class 2606 OID 17916)
-- Name: bvtcat4_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtcat4
    ADD CONSTRAINT bvtcat4_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2164 (class 2606 OID 17831)
-- Name: bvtmenu_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2163 (class 2606 OID 17826)
-- Name: bvtmenu_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY bvtmenu
    ADD CONSTRAINT bvtmenu_rol_fk FOREIGN KEY (b_codrol, instancia) REFERENCES bvt003(codrol, instancia);


--
-- TOC entry 2165 (class 2606 OID 16974)
-- Name: instancias_usr_fk2; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY instancias_usr
    ADD CONSTRAINT instancias_usr_fk2 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2166 (class 2606 OID 16979)
-- Name: mailgrupos_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY mailgrupos
    ADD CONSTRAINT mailgrupos_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2167 (class 2606 OID 17793)
-- Name: maillista_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2168 (class 2606 OID 17883)
-- Name: maillista_idgrupo_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY maillista
    ADD CONSTRAINT maillista_idgrupo_fk FOREIGN KEY (idgrupo, instancia) REFERENCES mailgrupos(idgrupo, instancia);


--
-- TOC entry 2169 (class 2606 OID 17802)
-- Name: t_programacion_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_fk1 FOREIGN KEY (instancia) REFERENCES instancias(instancia);


--
-- TOC entry 2170 (class 2606 OID 17888)
-- Name: t_programacion_idgrupo_fk1; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_idgrupo_fk1 FOREIGN KEY (idgrupo, instancia) REFERENCES mailgrupos(idgrupo, instancia);


--
-- TOC entry 2171 (class 2606 OID 17893)
-- Name: t_programacion_rep_fk; Type: FK CONSTRAINT; Schema: public; Owner: bizview
--

ALTER TABLE ONLY t_programacion
    ADD CONSTRAINT t_programacion_rep_fk FOREIGN KEY (codrep, instancia) REFERENCES bvt001(codrep, instancia);


--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-12-26 11:45:42 VET

--
-- PostgreSQL database dump complete
--

