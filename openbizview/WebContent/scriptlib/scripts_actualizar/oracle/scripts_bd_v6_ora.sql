ALTER TABLE BVT001  
MODIFY (CODGRUP NOT NULL);

ALTER TABLE BVT001
ADD CONSTRAINT BVT001_FK2 FOREIGN KEY
(
  CODGRUP 
, INSTANCIA 
)
REFERENCES BVT001A
(
  CODGRUP 
, INSTANCIA 
)
ENABLE;


ALTER TABLE T_PROGRAMACION  
MODIFY (CODREP VARCHAR2(15 BYTE) );



create or replace FUNCTION   "COUNT_BVTMENU" (pbusqueda varchar2, pcodrol varchar2, pinstancia varchar2)
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

create or replace FUNCTION           "COUNT_BVT005" (pbusqueda varchar2, prol varchar2, pinstancia varchar2)
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

create or replace FUNCTION           "COUNT_ACCCAT1" (pbusqueda varchar2, pcodrol varchar2, pinstancia varchar2)
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



create or replace FUNCTION           "COUNT_ACCCAT2" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pinstancia varchar2)
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




create or replace FUNCTION           "COUNT_ACCCAT3" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pcodcat2 varchar2, pinstancia varchar2)
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





create or replace FUNCTION           "COUNT_ACCCAT4" (pbusqueda varchar2, pcodrol varchar2, pcodcat1 varchar2, pcodcat2 varchar2, pcodcat3 varchar2, pinstancia varchar2)
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


create or replace FUNCTION           "COUNT_MAILLISTA" (pbusqueda varchar2, pidgrupo varchar2, pinstancia varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM  MAILGRUPOS A, MAILLISTA B
WHERE A.IDGRUPO=B.IDGRUPO
and a.instancia = b.instancia
and a.idgrupo||b.idmail||UPPER(b.mail) like  '%'||pbusqueda||'%'
and  a.idgrupo like pidgrupo||'%'
and b.instancia = pinstancia;
return vcount;
end;



create or replace FUNCTION           "COUNT_BVT007" (pbusqueda varchar2, prol varchar2, pinstancia varchar2)
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


ALTER TABLE BVT002  
MODIFY (INSTANCIA DEFAULT 0 );


ALTER TABLE BVT002
ADD CONSTRAINT BVT002_FK1 FOREIGN KEY
(
  INSTANCIA 
)
REFERENCES INSTANCIAS
(
  INSTANCIA 
)
ENABLE;


ALTER TABLE BVT002
ADD CONSTRAINT BVT002_FK2 FOREIGN KEY
(
  B_CODROL 
, INSTANCIA 
)
REFERENCES BVT003
(
  CODROL 
, INSTANCIA 
)
ENABLE;


ALTER TABLE BVT002  
MODIFY (INSTANCIA INTEGER DEFAULT NULL NOT NULL);

DROP INDEX BVT003_PK;

ALTER INDEX BVT003_PK1 
RENAME TO BVT003_PK;

ALTER TABLE T_PROGRAMACION 
ADD (DIAS_SEMANA VARCHAR2(30) );

COMMENT ON COLUMN T_PROGRAMACION.DIAS_SEMANA IS 'DIAS DE LA SEMANA';



create or replace FUNCTION  COUNT_PROGRAMACION (pbusqueda varchar2, pinstancia varchar2, preporte varchar2, pfrecuencia varchar2, pgrupo varchar2)
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


ALTER TABLE BVTPARAMS_TEMP 
ADD (PARAMHIDDEN VARCHAR2(5) NOT NULL);

ALTER TABLE BVTPARAMS_TEMP 
ADD (PARAMREQUIRED VARCHAR2(5) NOT NULL);

COMMENT ON COLUMN BVTPARAMS_TEMP.PARAMHIDDEN IS 'Indica si el parámetro es hidden';

COMMENT ON COLUMN BVTPARAMS_TEMP.PARAMREQUIRED IS 'Indica si es requerido';

CREATE INDEX BVT001_INDEX1 ON BVT001 (INSTANCIA);

CREATE INDEX BVT006_INDEX1 ON BVT006 (B_CODUSER, INSTANCIA);



ALTER TABLE T_PROGRAMACION  
MODIFY (DIAINICIO DATE NOT NULL);


ALTER TABLE T_PROGRAMACION 
DROP COLUMN HORA;

create or replace FUNCTION  COUNT_PROGRAMACION (pbusqueda varchar2, pinstancia varchar2, preporte varchar2, pfrecuencia varchar2, pgrupo varchar2, ptrigger varchar2)
  RETURN number AS
vcount number;
begin
select count(*) into vcount 
FROM  t_programacion
WHERE job||codrep like  '%'||pbusqueda||'%'
and codrep like preporte||'%'
and frecuencia like pfrecuencia||'%'
and disparador like ptrigger||'%'
and idgrupo like pgrupo||'%'
and instancia = pinstancia;
return vcount;
end;


--------------------------------------------------------
-- Archivo creado  - martes-abril-26-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BVT008
--------------------------------------------------------

  CREATE TABLE "BVT008" 
   (	"CODUSER" VARCHAR2(10 BYTE), 
	"B_CODROL" VARCHAR2(5 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT008"."CODUSER" IS 'CÓDIGO DE USUARIO';
   COMMENT ON COLUMN "BVT008"."B_CODROL" IS 'CÓDIGO DEL ROL';
   COMMENT ON COLUMN "BVT008"."USRCRE" IS 'USUARIO DE CREACIÓN DE REGISTRO';
   COMMENT ON COLUMN "BVT008"."FECCRE" IS 'FECHA DE CREACIÓN DE REGISTRO';
   COMMENT ON COLUMN "BVT008"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "BVT008"  IS 'ROLES ADICIONALES';
--------------------------------------------------------
--  DDL for Index BVT008_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT008_PK" ON "BVT008" ("CODUSER", "B_CODROL", "INSTANCIA") 
  ;
--------------------------------------------------------
--  Constraints for Table BVT008
--------------------------------------------------------

  ALTER TABLE "BVT008" ADD CONSTRAINT "BVT008_PK" PRIMARY KEY ("CODUSER", "B_CODROL", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT008" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT008" MODIFY ("B_CODROL" NOT NULL ENABLE);
  ALTER TABLE "BVT008" MODIFY ("CODUSER" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table BVT008
--------------------------------------------------------

  ALTER TABLE "BVT008" ADD CONSTRAINT "BVT008_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "BVT008" ADD CONSTRAINT "BVT008_FK2" FOREIGN KEY ("B_CODROL", "INSTANCIA")
	  REFERENCES "BVT003" ("CODROL", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT008" ADD CONSTRAINT "BVT008_FK3" FOREIGN KEY ("CODUSER")
	  REFERENCES "BVT002" ("CODUSER") ENABLE;
