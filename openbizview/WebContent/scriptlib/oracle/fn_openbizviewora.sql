--------------------------------------------------------
-- Archivo creado  - sábado-abril-30-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Function COUNT_ACCCAT1
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_ACCCAT1" (pbusqueda varchar2, pcodrol varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from acccat1 a, bvtcat1 b
where a.b_codcat1 = b.codcat1
and  a.b_codcat1||b.descat1  like '%'||pbusqueda||'%'
AND  a.b_codrol like pcodrol||'%'
and a.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_ACCCAT2
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_ACCCAT2" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from acccat2 a, bvtcat1 b, bvtcat2 c
WHERE a.b_codcat1=b.codcat1
and  a.b_codcat1=c.b_codcat1
and  a.b_codcat2=c.codcat2
and  a.b_codcat1||b.descat1||a.b_codcat2||c.descat2 like '%'||pbusqueda||'%'
and  a.b_codcat1 like pcodcat1||'%'
AND  a.b_codrol like pcodrol||'%'
and a.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_ACCCAT3
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_ACCCAT3" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pcodcat2 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from   acccat3 a, bvtcat1 b, bvtcat2 c, bvtcat3 d
where a.b_codcat1=b.codcat1
and   a.b_codcat1=c.b_codcat1
and   a.b_codcat2=c.codcat2
and   a.b_codcat1=d.b_codcat1
and   a.b_codcat2=d.b_codcat2
and   a.b_codcat3=d.codcat3
and   a.b_codcat1||b.descat1||a.b_codcat2||c.descat2||a.b_codcat3||d.descat3 like  '%'||pbusqueda||'%'
and  a.b_codcat1 like pcodcat1||'%'
and  a.b_codcat2 like pcodcat2||'%'
AND  a.b_codrol like pcodrol||'%'
and a.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_ACCCAT4
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_ACCCAT4" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pcodcat2 varchar2, pcodcat3 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
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
and   a.b_codcat1||b.descat1||a.b_codcat2||c.descat2||a.b_codcat3||d.descat3||a.b_codcat4||e.descat4 like '%'||pbusqueda||'%'
and   a.b_codcat1 like pcodcat1||'%'
and   a.b_codcat2 like pcodcat2||'%'
and   a.b_codcat3 like pcodcat3||'%'
AND   a.b_codrol like pcodrol||'%'
and a.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BIAUDIT
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BIAUDIT" (pbusqueda varchar2, pfecha varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
if pfecha = '0' then
select count(*) into vcount 
from biaudit
where  fechadia||substr(fecacc,12,22)||detfaz||negocio like '%'||pbusqueda||'%'
and instancia = pinstancia;
else 
select count(*) into vcount 
from biaudit
where  fechadia||substr(fecacc,12,22)||detfaz||negocio like '%'||pbusqueda||'%'
and fechadia = pfecha
and instancia = pinstancia;
end if;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT001
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT001" (pbusqueda varchar2, pcodrol varchar2, pgrupo varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
if pgrupo is null then
select count(*) into vcount 
from bvt001
where codrep||desrep  like '%'||pbusqueda||'%'
AND  CODREP  IN (SELECT B_CODREP FROM BVT007 WHERE B_CODROL = pcodrol)
and  instancia = pinstancia;
else
select count(*) into vcount 
from bvt001
where codrep||desrep  like '%'||pbusqueda||'%'
AND  CODREP  IN (SELECT B_CODREP FROM BVT007 WHERE B_CODROL = pcodrol)
and  codgrup like pgrupo||'%'
and  instancia = pinstancia;
end if;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT001A
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT001A" (pbusqueda varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvt001a
where codgrup||desgrup  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT002
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT002" (pbusqueda varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvt002
where coduser||desuser  like '%'||pbusqueda||'%';
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT003
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT003" (pbusqueda varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvt003
where codrol||desrol  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT005
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT005" (pbusqueda varchar2, prol varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM Bvt005
WHERE b_codrol||codopc||desopc like  '%'||pbusqueda||'%'
and b_codrol like prol||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT006
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT006" (pbusqueda varchar2, pcoduser varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvt006
where b_codrep||b_desrep  like '%'||pbusqueda||'%'
AND  B_CODUSER like pcoduser||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVT007
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVT007" (pbusqueda varchar2, prol varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM Bvt007 a, bvt003 b, bvt001 c
WHERE a.b_codrol=b.codrol
and   a.b_codrep=c.codrep
and a.b_codrol||b.desrol||a.b_codrep||c.desrep like  '%'||pbusqueda||'%'
and a.b_codrol like prol||'%'
and a.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVTCAT1
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVTCAT1" (pbusqueda varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvtcat1
where codcat1||descat1  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVTCAT2
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVTCAT2" (pbusqueda varchar2, pbcodcat1 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvtcat2
where b_codcat1 like pbcodcat1||'%' 
and codcat2||descat2  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVTCAT3
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVTCAT3" (pbusqueda varchar2, pbcodcat1 varchar2, pbcodcat2 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvtcat3
where b_codcat1 like pbcodcat1||'%' 
and b_codcat2 like pbcodcat2||'%'
and codcat3||descat3  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVTCAT4
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVTCAT4" (pbusqueda varchar2, pbcodcat1 varchar2, pbcodcat2 varchar2, pbcodcat3 varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvtcat4
where b_codcat1 like pbcodcat1||'%' 
and b_codcat2 like pbcodcat2||'%'
and b_codcat3 like pbcodcat3||'%'
and codcat4||descat4  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_BVTMENU
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_BVTMENU" (pbusqueda varchar2, pcodrol varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from bvtmenu
where b_codrol||codopc||desopc  like '%'||pbusqueda||'%'
AND  b_codrol  like pcodrol||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_INSTANCIAS
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_INSTANCIAS" (pbusqueda varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from instancias
where instancia||descripcion  like '%'||pbusqueda||'%';
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_INSTANCIASUSR
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_INSTANCIASUSR" (pbusqueda varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from instancias_usr a, instancias b
where a.instancia=b.instancia
and  a.coduser||a.instancia||b.descripcion  like '%'||pbusqueda||'%'
AND  a.instancia like pinstancia||'%';
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_MAILGRUPO
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_MAILGRUPO" (pbusqueda varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
from mailgrupos
where idgrupo||desgrupo  like '%'||pbusqueda||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_MAILLISTA
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_MAILLISTA" (pbusqueda varchar2, pidgrupo varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM  MAILGRUPOS A, MAILLISTA B
WHERE A.IDGRUPO=B.IDGRUPO
and a.instancia = b.instancia
and a.idgrupo||b.idmail||upper(b.mail) like  '%'||pbusqueda||'%'
and  a.idgrupo like pidgrupo||'%'
and b.instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function COUNT_PROGRAMACION
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "COUNT_PROGRAMACION" (pbusqueda varchar2, pinstancia varchar2, preporte varchar2, pfrecuencia varchar2, pgrupo varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||codrep like  '%'||pbusqueda||'%'
and codrep like preporte||'%'
and frecuencia like pfrecuencia||'%'
and idgrupo like pgrupo||'%'
and instancia = pinstancia;
return vcount;
end;

/
--------------------------------------------------------
--  DDL for Function SPLITCAD
--------------------------------------------------------

  CREATE OR REPLACE FUNCTION "SPLITCAD" (CADENA   VARCHAR2,
                   POSICION NUMBER,
                   DELIM    VARCHAR2 := '|')
 RETURN    VARCHAR2
 IS
    START_POS NUMBER;
    END_POS   NUMBER;
 BEGIN
    IF POSICION = 1 THEN
        START_POS := 1;
    ELSE
        START_POS := INSTR(CADENA, DELIM, 1, POSICION - 1);
        IF START_POS = 0 THEN
            RETURN NULL;
        ELSE
            START_POS := START_POS + LENGTH(DELIM);
        END IF;
    END IF;
    END_POS := INSTR(CADENA, DELIM, START_POS, 1);
    IF END_POS = 0 THEN
        RETURN SUBSTR(CADENA, START_POS);
    ELSE
        RETURN SUBSTR(CADENA, START_POS, END_POS - START_POS);
    END IF;
 END SPLITCAD;

/
