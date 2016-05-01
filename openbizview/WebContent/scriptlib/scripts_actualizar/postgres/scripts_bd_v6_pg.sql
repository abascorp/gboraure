ALTER TABLE public.bvt001
   ALTER COLUMN codgrup SET NOT NULL;
COMMENT ON COLUMN public.bvt001.codgrup IS 'Código de grupo';

-- Function: public.count_bvtmenu(character, character, character)

-- DROP FUNCTION public.count_bvtmenu(character, character, character);



CREATE OR REPLACE FUNCTION public.count_bvtmenu(
    pbusqueda character,
    pcodrol character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
vcount bigint;
begin
select count(*) into vcount 
from bvtmenu
where b_codrol||codopc||desopc  like '%'||pbusqueda||'%'
AND  b_codrol  like pcodrol||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_bvtmenu(character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_bvtmenu(character, character, character) IS 'Cuenta registros de opciones roles';



ALTER TABLE public.bvt001
  ADD CONSTRAINT bvt001_fk2 FOREIGN KEY (codgrup, instancia) REFERENCES public.bvt001a (codgrup, instancia) ON UPDATE NO ACTION ON DELETE NO ACTION;
COMMENT ON TABLE public.bvt001
  IS 'Reportes';
  
  
  
  -- Function: public.count_bvt005(character, character, character)

-- DROP FUNCTION public.count_bvt005(character, character, character);

CREATE OR REPLACE FUNCTION public.count_bvt005(
    pbusqueda character,
    prol character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
vcount bigint;
begin
select count(*) into vcount 
FROM Bvt005
WHERE b_codrol||codopc||desopc like  '%'||pbusqueda||'%'
and b_codrol like prol||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_bvt005(character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_bvt005(character, character, character) IS 'Cuenta registros de acceso a botones';



-- Function: public.count_acccat1(character, character, character)

-- DROP FUNCTION public.count_acccat1(character, character, character);

CREATE OR REPLACE FUNCTION public.count_acccat1(
    pbusqueda character,
    pcodrol character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
vcount bigint;
begin
select count(*) into vcount 
from acccat1 a, bvtcat1 b
where a.b_codcat1 = b.codcat1
and  a.b_codcat1||b.descat1  like '%'||pbusqueda||'%'
AND  a.b_codrol like pcodrol||'%'
and cast(a.instancia as text) = pinstancia;
return vcount;
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_acccat1(character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_acccat1(character, character, character) IS 'Cuenta registros de acceso a categoria1';



-- Function: public.count_acccat2(character, character, character, character)

-- DROP FUNCTION public.count_acccat2(character, character, character, character);

CREATE OR REPLACE FUNCTION public.count_acccat2(
    pbusqueda character,
    pcodrol character,
    pcodcat1 character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
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
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_acccat2(character, character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_acccat2(character, character, character, character) IS 'Cuenta registros de acceso a categoria2';





-- Function: public.count_acccat3(character, character, character, character, character)

-- DROP FUNCTION public.count_acccat3(character, character, character, character, character);

CREATE OR REPLACE FUNCTION public.count_acccat3(
    pbusqueda character,
    pcodrol character,
    pcodcat1 character,
    pcodcat2 character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
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
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_acccat3(character, character, character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_acccat3(character, character, character, character, character) IS 'Cuenta registros de acceso a categoria3';



Indica si es requerido
-- Function: public.count_acccat4(character, character, character, character, character)

-- DROP FUNCTION public.count_acccat4(character, character, character, character, character);

CREATE OR REPLACE FUNCTION public.count_acccat4(
    pbusqueda character,
    pcodrol character,
    pcodcat1 character,
    pcodcat2 character,
    pcodcat3 character)
  RETURNS bigint AS
$BODY$declare
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
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_acccat4(character, character, character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_acccat4(character, character, character, character, character) IS 'Cuenta registros de acceso a categoria4';




-- Function: public.count_maillista(character, character, character)

-- DROP FUNCTION public.count_maillista(character, character, character);

CREATE OR REPLACE FUNCTION public.count_maillista(
    pbusqueda character,
    pidgrupo character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
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
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_maillista(character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_maillista(character, character, character) IS 'Cuenta registros de lista de correos';


-- Function: public.count_bvt007(character, character, character)

-- DROP FUNCTION public.count_bvt007(character, character, character);

CREATE OR REPLACE FUNCTION public.count_bvt007(
    pbusqueda character,
    prol character,
    pinstancia character)
  RETURNS bigint AS
$BODY$declare
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
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_bvt007(character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_bvt007(character, character, character) IS 'Cuenta registros de acceso a reportes';


ALTER TABLE public.bvt002
  ADD CONSTRAINT bvt002_fk1 FOREIGN KEY (instancia) REFERENCES public.instancias (instancia) ON UPDATE NO ACTION ON DELETE NO ACTION;
  
  ALTER TABLE public.bvt002
  ADD CONSTRAINT bvt002_fk2 FOREIGN KEY (b_codrol, instancia) REFERENCES public.bvt003 (codrol, instancia) ON UPDATE NO ACTION ON DELETE NO ACTION;


ALTER TABLE public.bvt002
   ALTER COLUMN instancia DROP DEFAULT;
ALTER TABLE public.bvt002
   ALTER COLUMN instancia SET NOT NULL;
COMMENT ON COLUMN public.bvt002.instancia IS 'Instancia asociada al usuario';


-- Function: public.count_programacion(character, character, character, character, character)

-- Function: public.count_programacion(character, character, character, character, character)

 DROP FUNCTION public.count_programacion(character, character, character, character, character);

CREATE OR REPLACE FUNCTION public.count_programacion(
    pbusqueda character,
    pinstancia character,
    preporte character,
    pfrecuencia character,
    pgrupo character,
    ptrigger character)
  RETURNS bigint AS
$BODY$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||disparador||codrep like  '%'||pbusqueda||'%'
and codrep like preporte||'%'
and frecuencia like pfrecuencia||'%'
and cast(idgrupo as text) like pgrupo||'%'
and disparador like ptrigger||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_programacion(character, character, character, character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_programacion(character, character, character, character, character, character) IS 'Cuenta registros de programación';



ALTER TABLE public.t_programacion
   ALTER COLUMN codrep TYPE character(15);
COMMENT ON COLUMN public.t_programacion.codrep IS 'Código de reporte a enviar';

ALTER TABLE public.t_programacion
  ADD COLUMN dias_semana character(30);
COMMENT ON COLUMN public.t_programacion.dias_semana IS 'Dias de la semana';

ALTER TABLE public.bvtparams_temp
  ADD COLUMN paramhidden character(5) NOT NULL;
ALTER TABLE public.bvtparams_temp
  ADD COLUMN paramrequired character(5) NOT NULL;
  
  
  COMMENT ON COLUMN public.bvtparams_temp.paramhidden
  IS 'Indica si el parámetro es hidden';
COMMENT ON COLUMN public.bvtparams_temp.paramrequired
  IS 'Indica si es requerido';
COMMENT ON COLUMN public.bvtparams_temp.paramhidden IS 'Indica si el parámetro es hidden';
COMMENT ON COLUMN public.bvtparams_temp.paramrequired IS 'Indica si es requerido';

CREATE INDEX bvt001_index1
   ON public.bvt001 (instancia ASC NULLS LAST);
   
   CREATE INDEX bvt006_index1
   ON public.bvt006 (b_coduser ASC NULLS LAST, instancia ASC NULLS LAST);
   
ALTER TABLE public.t_programacion
   ALTER COLUMN diainicio TYPE timestamp without time zone NOT NULL;
COMMENT ON COLUMN public.t_programacion.diainicio IS 'Día de inicio para intérvalos mensuales';



ALTER TABLE public.t_programacion
  DROP COLUMN hora;
  
  -- Function: public.count_programacion(character, character, character, character, character)

-- DROP FUNCTION public.count_programacion(character, character, character, character, character);

-- Function: public.count_programacion(character, character, character, character, character)

-- DROP FUNCTION public.count_programacion(character, character, character, character, character);

CREATE OR REPLACE FUNCTION public.count_programacion(
    pbusqueda character,
    pinstancia character,
    preporte character,
    pfrecuencia character,
    pgrupo character)
  RETURNS bigint AS
$BODY$declare
vcount bigint;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||codrep||trim(idgrupo)||diainicio like  '%'||pbusqueda||'%'
and codrep like preporte||'%'
and frecuencia like pfrecuencia||'%'
and cast(idgrupo as text) like pgrupo||'%'
and cast(instancia as text) = pinstancia;
return vcount;
end;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.count_programacion(character, character, character, character, character)
  OWNER TO openbizview;
COMMENT ON FUNCTION public.count_programacion(character, character, character, character, character) IS 'Cuenta registros de programación';


-- Table: public.bvt008

-- DROP TABLE public.bvt008;

CREATE TABLE public.bvt008
(
  coduser character(10) NOT NULL, -- Código de usuario
  b_codrol character(5) NOT NULL, -- Código del rol
  usrcre character(10), -- Usuario de creación de registro
  feccre date, -- Fecha de creación de registro
  usract character(10), -- Usuario de actualización de registro
  CONSTRAINT bvt008_pk PRIMARY KEY (coduser, b_codrol, instancia),
  CONSTRAINT bvt008_fk1 FOREIGN KEY (instancia)
      REFERENCES public.instancias (instancia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT bvt008_fk2 FOREIGN KEY (b_codrol, instancia)
      REFERENCES public.bvt003 (codrol, instancia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT bvt008_fk3 FOREIGN KEY (coduser)
      REFERENCES public.bvt002 (coduser) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.bvt008
  OWNER TO openbizview;
COMMENT ON TABLE public.bvt008
  IS 'Roles adicionales';
COMMENT ON COLUMN public.bvt008.coduser IS 'Código de usuario';
COMMENT ON COLUMN public.bvt008.b_codrol IS 'Código del rol';
COMMENT ON COLUMN public.bvt008.usrcre IS 'Usuario de creación de registro';
COMMENT ON COLUMN public.bvt008.feccre IS 'Fecha de creación de registro';
COMMENT ON COLUMN public.bvt008.instancia IS 'Instancia de usuario';



