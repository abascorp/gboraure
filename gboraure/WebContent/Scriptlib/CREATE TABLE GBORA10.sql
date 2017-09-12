
  CREATE TABLE "OPENBIZVIEW"."GBORA10" 
   (	"CODGRA" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODSEC" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODPOZ" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"ESTATUS" VARCHAR2(10 BYTE), 
	"QLS" FLOAT(126), 
	"FECAFO" DATE NOT NULL ENABLE, 
	"OBSERV" VARCHAR2(60 BYTE), 
	"USRCRE" VARCHAR2(30 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(30 BYTE), 
	"FECACT" DATE, 
	 CONSTRAINT "GBORA10_PK" PRIMARY KEY ("CODGRA", "CODSEC", "CODPOZ", "FECAFO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "GBORA10_FK1" FOREIGN KEY ("CODGRA", "CODSEC", "CODPOZ")
	  REFERENCES "OPENBIZVIEW"."GBORA08" ("CODGRA", "CODSEC", "CODPOZ") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."CODGRA" IS 'CODIGO DE LA GRANJA';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."CODSEC" IS 'CODIGO DEL SECTOR ASOCIADO A LOS POZOS';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."CODPOZ" IS 'CODIGO DEL POZO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."ESTATUS" IS 'ESTATUS DEL POZO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."QLS" IS 'LITROS POR SEGUNDO ';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."FECAFO" IS 'FECHA DEL AFORO DEL POZO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."OBSERV" IS 'OBSERVACIONES';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."USRCRE" IS 'USUARIO QUE CREO EL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."FECCRE" IS 'FECHA DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."USRACT" IS 'USUARIO QUE ACTUALIZO EL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA10"."FECACT" IS 'FECHA DE ACTUALIZACION DEL REGISTRO';
