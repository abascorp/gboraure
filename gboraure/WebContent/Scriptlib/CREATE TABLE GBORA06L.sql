
  CREATE TABLE "OPENBIZVIEW"."GBORA06L" 
   (	"CODGRA" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"FECARR" DATE NOT NULL ENABLE, 
	"CODSEC" VARCHAR2(4 BYTE) NOT NULL ENABLE, 
	"KILOS" FLOAT(126) NOT NULL ENABLE, 
	"USRCRE" VARCHAR2(50 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(50 BYTE), 
	"FECACT" DATE, 
	 CONSTRAINT "GBORA06L_PK" PRIMARY KEY ("CODGRA", "FECARR", "CODSEC")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "GBORA06L_FK1" FOREIGN KEY ("CODGRA", "CODSEC")
	  REFERENCES "OPENBIZVIEW"."GBORA02" ("CODGRA", "CODSEC") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."CODGRA" IS 'CODIGO DE GRANJA';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."FECARR" IS 'FECHA DE ARRIME';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."CODSEC" IS 'CODIGO SECTOR';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."KILOS" IS 'TOTAL DE KILOS DELA RRIME';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."USRCRE" IS 'USUARIO QUE CREO EL REGISTOR';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."FECCRE" IS 'FECHA DE CREACION DEL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."USRACT" IS 'USUARIO QUE ACTUALIZO EL REGISTRO';
   COMMENT ON COLUMN "OPENBIZVIEW"."GBORA06L"."FECACT" IS 'FECHA DE ACTUALIZACION DEL REGISTRO';
