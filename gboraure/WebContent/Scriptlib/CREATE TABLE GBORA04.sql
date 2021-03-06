
  CREATE TABLE "OPENBIZVIEW"."GBORA04" 
   (	"CODGRA" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODSEC" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODLOT" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"CODTAB" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"DESTAB" VARCHAR2(60 BYTE) NOT NULL ENABLE, 
	"USRCRA" VARCHAR2(50 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(50 BYTE), 
	"FECACT" DATE, 
	 CONSTRAINT "GBORA04_PK" PRIMARY KEY ("CODGRA", "CODSEC", "CODLOT", "CODTAB")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "GBORA04_FK1" FOREIGN KEY ("CODGRA", "CODSEC", "CODLOT")
	  REFERENCES "OPENBIZVIEW"."GBORA03" ("CODGRA", "CODSEC", "CODLOT") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."CODGRA" IS 'CODIGO GRANJA';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."CODSEC" IS 'CODIGO DE SECTOR';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."CODLOT" IS 'CODIGO DE LOTE';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."CODTAB" IS 'CODIGO DEL TABLON';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."DESTAB" IS 'DESCRIPCION DEL TABLON';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."USRCRA" IS 'USUARIO DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."FECCRE" IS 'FECHA DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."USRACT" IS 'USUARIO QUE ACTUALIZO EL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA04"."FECACT" IS 'FECHA DE ACTUALIZACION DEL REGISTRO';
