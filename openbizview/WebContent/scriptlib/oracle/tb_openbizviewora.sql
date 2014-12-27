--------------------------------------------------------
-- Archivo creado  - viernes-diciembre-26-2014   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACCCAT1
--------------------------------------------------------

  CREATE TABLE "ACCCAT1" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"B_CODCAT1" VARCHAR2(20 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "ACCCAT1"."B_CODROL" IS 'Codigo de rol';
   COMMENT ON COLUMN "ACCCAT1"."B_CODCAT1" IS 'Codigo de categoria1';
   COMMENT ON COLUMN "ACCCAT1"."USRCRE" IS 'usuario de creacion de registro';
   COMMENT ON COLUMN "ACCCAT1"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "ACCCAT1"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT1"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT1"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "ACCCAT1"  IS 'ACCESO A CATEGORIA1 PARA REPORTES';
--------------------------------------------------------
--  DDL for Table BIAUDIT
--------------------------------------------------------

  CREATE TABLE "BIAUDIT" 
   (	"FECACC" VARCHAR2(30 BYTE), 
	"DETFAZ" VARCHAR2(500 BYTE), 
	"RESULT" VARCHAR2(20 BYTE), 
	"NEGOCIO" VARCHAR2(20 BYTE), 
	"FECHADIA" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BIAUDIT"."FECACC" IS 'Fecha de interfaz';
   COMMENT ON COLUMN "BIAUDIT"."DETFAZ" IS 'Detalle de al interfaz';
   COMMENT ON COLUMN "BIAUDIT"."RESULT" IS 'Resultado';
   COMMENT ON COLUMN "BIAUDIT"."NEGOCIO" IS 'Negocio al cual se realizo la interfaz';
   COMMENT ON COLUMN "BIAUDIT"."FECHADIA" IS 'Fecha del dia';
   COMMENT ON TABLE "BIAUDIT"  IS 'Interfaz derivados y precocidos a Oracle';
--------------------------------------------------------
--  DDL for Table ACCCAT4
--------------------------------------------------------

  CREATE TABLE "ACCCAT4" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"B_CODCAT2" VARCHAR2(10 BYTE), 
	"B_CODCAT3" VARCHAR2(10 BYTE), 
	"B_CODCAT4" VARCHAR2(20 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "ACCCAT4"."B_CODROL" IS 'Rol de usuario';
   COMMENT ON COLUMN "ACCCAT4"."B_CODCAT1" IS 'Categoria 1';
   COMMENT ON COLUMN "ACCCAT4"."B_CODCAT2" IS 'Categoria 2';
   COMMENT ON COLUMN "ACCCAT4"."B_CODCAT3" IS 'Categoria 3';
   COMMENT ON COLUMN "ACCCAT4"."B_CODCAT4" IS 'Categoria 4';
   COMMENT ON COLUMN "ACCCAT4"."USRCRE" IS 'Usuario de creación de registro';
   COMMENT ON COLUMN "ACCCAT4"."FECCRE" IS 'Fecha de creación de registro';
   COMMENT ON COLUMN "ACCCAT4"."USRACT" IS 'Usuario de actualización de registro';
   COMMENT ON COLUMN "ACCCAT4"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT4"."INSTANCIA" IS 'Instancia de usuario';
--------------------------------------------------------
--  DDL for Table BVT001A
--------------------------------------------------------

  CREATE TABLE "BVT001A" 
   (	"CODGRUP" VARCHAR2(10 BYTE), 
	"DESGRUP" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT001A"."CODGRUP" IS 'Codigo de grupo';
   COMMENT ON COLUMN "BVT001A"."DESGRUP" IS 'Descripcion de grupo';
   COMMENT ON COLUMN "BVT001A"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVT001A"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "BVT001A"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVT001A"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVT001A"."INSTANCIA" IS 'Instancia de usuarios';
   COMMENT ON TABLE "BVT001A"  IS 'Agruparción de reportes';
--------------------------------------------------------
--  DDL for Table BVT003A
--------------------------------------------------------

  CREATE TABLE "BVT003A" 
   (	"CODROL" VARCHAR2(5 BYTE), 
	"CODUSER" VARCHAR2(10 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT003A"."CODROL" IS 'Código de rol';
   COMMENT ON COLUMN "BVT003A"."CODUSER" IS 'Código del usuario';
   COMMENT ON COLUMN "BVT003A"."USRCRE" IS 'Usuario de creación de registro';
   COMMENT ON COLUMN "BVT003A"."FECCRE" IS 'Fecha de creación de registro';
   COMMENT ON COLUMN "BVT003A"."USRACT" IS 'Usuario de actualización de registro';
   COMMENT ON COLUMN "BVT003A"."FECACT" IS 'Fecha de actualización de registro';
   COMMENT ON COLUMN "BVT003A"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "BVT003A"  IS 'Roles adicionales usuarios';
--------------------------------------------------------
--  DDL for Table BVT003
--------------------------------------------------------

  CREATE TABLE "BVT003" 
   (	"CODROL" VARCHAR2(5 BYTE), 
	"DESROL" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT003"."CODROL" IS 'CODIGO DEL ROL';
   COMMENT ON COLUMN "BVT003"."DESROL" IS 'DESCRIPCION DEL ROL';
   COMMENT ON COLUMN "BVT003"."USRCRE" IS 'USUARIO DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT003"."FECCRE" IS 'FECHA DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT003"."USRACT" IS 'USUARIO QUE ACTUALIZ? EL REGISTRO';
   COMMENT ON COLUMN "BVT003"."FECACT" IS 'FECHA DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVT003"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "BVT003"  IS 'ROLES';
--------------------------------------------------------
--  DDL for Table ACCCAT2
--------------------------------------------------------

  CREATE TABLE "ACCCAT2" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"B_CODCAT2" VARCHAR2(10 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" VARCHAR2(20 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "ACCCAT2"."B_CODROL" IS 'Codigo de rol';
   COMMENT ON COLUMN "ACCCAT2"."B_CODCAT1" IS 'Codigo de categoria1';
   COMMENT ON COLUMN "ACCCAT2"."B_CODCAT2" IS 'Codigo de categoria2';
   COMMENT ON COLUMN "ACCCAT2"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "ACCCAT2"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "ACCCAT2"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT2"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT2"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "ACCCAT2"  IS 'ACCESO A CATEGORIA2 PARA REPORTE';
--------------------------------------------------------
--  DDL for Table BVT002
--------------------------------------------------------

  CREATE TABLE "BVT002" 
   (	"CODUSER" VARCHAR2(10 BYTE), 
	"DESUSER" VARCHAR2(50 BYTE), 
	"CLUSER" VARCHAR2(150 BYTE), 
	"B_CODROL" VARCHAR2(5 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"MAIL" VARCHAR2(150 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT002"."CODUSER" IS 'CODIGO DE USUARIO';
   COMMENT ON COLUMN "BVT002"."DESUSER" IS 'NOMBRE DE USUARIO';
   COMMENT ON COLUMN "BVT002"."CLUSER" IS 'CLAVE DE USUARIO';
   COMMENT ON COLUMN "BVT002"."B_CODROL" IS 'CODIGO DEL ROL';
   COMMENT ON COLUMN "BVT002"."USRCRE" IS 'USUARIO DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT002"."FECCRE" IS 'FECHA DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT002"."USRACT" IS 'USUARIO QUE ACTUALIZ? EL REGISTRO';
   COMMENT ON COLUMN "BVT002"."FECACT" IS 'FECHA DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVT002"."MAIL" IS 'DIRECCIÓN DE CORREO ELECTRÓNICO';
   COMMENT ON COLUMN "BVT002"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "BVT002"  IS 'USUARIOS';
--------------------------------------------------------
--  DDL for Table BVT001
--------------------------------------------------------

  CREATE TABLE "BVT001" 
   (	"CODREP" VARCHAR2(15 BYTE), 
	"DESREP" VARCHAR2(50 BYTE), 
	"COMREP" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"CODGRUP" VARCHAR2(10 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT001"."CODREP" IS 'CODIGO DE REPORTE';
   COMMENT ON COLUMN "BVT001"."DESREP" IS 'DESCRIPCION DEL REPORTE';
   COMMENT ON COLUMN "BVT001"."COMREP" IS 'COMENTARIO ADICIONAL';
   COMMENT ON COLUMN "BVT001"."USRCRE" IS 'USUARIO DE CREACION';
   COMMENT ON COLUMN "BVT001"."FECCRE" IS 'FECHA DE CREACION';
   COMMENT ON COLUMN "BVT001"."USRACT" IS 'USUARIO QUE ACTUALIZ? EL REGISTRO';
   COMMENT ON COLUMN "BVT001"."FECACT" IS 'FECHA DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVT001"."CODGRUP" IS 'AGRUPACION DE REPORTES';
   COMMENT ON COLUMN "BVT001"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "BVT001"  IS 'REPORTES';
--------------------------------------------------------
--  DDL for Table ACCCAT3
--------------------------------------------------------

  CREATE TABLE "ACCCAT3" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"B_CODCAT2" VARCHAR2(10 BYTE), 
	"B_CODCAT3" VARCHAR2(10 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "ACCCAT3"."B_CODROL" IS 'Codigo de rol';
   COMMENT ON COLUMN "ACCCAT3"."B_CODCAT1" IS 'Codigo de categoria 1';
   COMMENT ON COLUMN "ACCCAT3"."B_CODCAT2" IS 'Codigo de categoria 2';
   COMMENT ON COLUMN "ACCCAT3"."B_CODCAT3" IS 'Codigo de categoria 3';
   COMMENT ON COLUMN "ACCCAT3"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "ACCCAT3"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "ACCCAT3"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT3"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "ACCCAT3"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "ACCCAT3"  IS 'ACCESO A CATEGORIA 3 PARA REPORTES';
--------------------------------------------------------
--  DDL for Table BVT005
--------------------------------------------------------

  CREATE TABLE "BVT005" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"CODOPC" VARCHAR2(2 BYTE), 
	"DESOPC" VARCHAR2(50 BYTE), 
	"CODVIS" VARCHAR2(1 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT005"."B_CODROL" IS 'ROL ACCESO A BOTONES';
   COMMENT ON COLUMN "BVT005"."CODOPC" IS 'NUMERO DE OPCION';
   COMMENT ON COLUMN "BVT005"."DESOPC" IS 'DESCRIPCION DE OPCION';
   COMMENT ON COLUMN "BVT005"."CODVIS" IS 'VISUALIZA 1, NO VISUALIZA 0';
   COMMENT ON COLUMN "BVT005"."USRCRE" IS 'USUARIO DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT005"."FECCRE" IS 'FECHA DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVT005"."USRACT" IS 'USUARIO DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVT005"."FECACT" IS 'FECHA DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVT005"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "BVT005"  IS 'ACCESO A BOTONES';
--------------------------------------------------------
--  DDL for Table BVT006
--------------------------------------------------------

  CREATE TABLE "BVT006" 
   (	"B_CODREP" VARCHAR2(15 BYTE), 
	"B_DESREP" VARCHAR2(50 BYTE), 
	"B_CODUSER" VARCHAR2(10 BYTE), 
	"FECACC" TIMESTAMP (6), 
	"INSTANCIA" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table BVT007
--------------------------------------------------------

  CREATE TABLE "BVT007" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"B_CODREP" VARCHAR2(15 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVT007"."B_CODROL" IS 'Codigo de rol';
   COMMENT ON COLUMN "BVT007"."B_CODREP" IS 'Codigo de reporte';
   COMMENT ON COLUMN "BVT007"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVT007"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "BVT007"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVT007"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVT007"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "BVT007"  IS 'ACCESO  A REPORTES POR ROL (NEGAR)';
--------------------------------------------------------
--  DDL for Table BVTCAT1
--------------------------------------------------------

  CREATE TABLE "BVTCAT1" 
   (	"CODCAT1" VARCHAR2(10 BYTE), 
	"DESCAT1" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTCAT1"."CODCAT1" IS 'Codigo de categoria 1';
   COMMENT ON COLUMN "BVTCAT1"."DESCAT1" IS 'Descripcion de categoria 1';
   COMMENT ON COLUMN "BVTCAT1"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVTCAT1"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "BVTCAT1"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT1"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT1"."INSTANCIA" IS 'Instancia';
   COMMENT ON TABLE "BVTCAT1"  IS 'Categoria1';
--------------------------------------------------------
--  DDL for Table BVTCAT2
--------------------------------------------------------

  CREATE TABLE "BVTCAT2" 
   (	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"CODCAT2" VARCHAR2(10 BYTE), 
	"DESCAT2" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTCAT2"."B_CODCAT1" IS 'Codigo de categoria 1';
   COMMENT ON COLUMN "BVTCAT2"."CODCAT2" IS 'Codigo de categoria 2';
   COMMENT ON COLUMN "BVTCAT2"."DESCAT2" IS 'Descripcion  de categoria 2';
   COMMENT ON COLUMN "BVTCAT2"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVTCAT2"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "BVTCAT2"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT2"."FECACT" IS 'fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT2"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "BVTCAT2"  IS 'Categoria2';
--------------------------------------------------------
--  DDL for Table BVTCAT3
--------------------------------------------------------

  CREATE TABLE "BVTCAT3" 
   (	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"B_CODCAT2" VARCHAR2(10 BYTE), 
	"CODCAT3" VARCHAR2(10 BYTE), 
	"DESCAT3" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTCAT3"."B_CODCAT1" IS 'Codigo de categoria 1';
   COMMENT ON COLUMN "BVTCAT3"."B_CODCAT2" IS 'Codigo de categoria2';
   COMMENT ON COLUMN "BVTCAT3"."CODCAT3" IS 'Codigo de categoria 3';
   COMMENT ON COLUMN "BVTCAT3"."DESCAT3" IS 'Descripcion de categoria 3';
   COMMENT ON COLUMN "BVTCAT3"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVTCAT3"."FECCRE" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT3"."USRACT" IS 'usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT3"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT3"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "BVTCAT3"  IS 'Categoria3';
--------------------------------------------------------
--  DDL for Table BVTCAT4
--------------------------------------------------------

  CREATE TABLE "BVTCAT4" 
   (	"B_CODCAT1" VARCHAR2(10 BYTE), 
	"B_CODCAT2" VARCHAR2(10 BYTE), 
	"B_CODCAT3" VARCHAR2(10 BYTE), 
	"CODCAT4" VARCHAR2(10 BYTE), 
	"DESCAT4" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"EQUICAT4" VARCHAR2(10 BYTE), 
	"TIPPRO" VARCHAR2(1 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTCAT4"."B_CODCAT1" IS 'Codigo de categoria1';
   COMMENT ON COLUMN "BVTCAT4"."B_CODCAT2" IS 'Codigo de categoria2';
   COMMENT ON COLUMN "BVTCAT4"."B_CODCAT3" IS 'Codigo de categoria3';
   COMMENT ON COLUMN "BVTCAT4"."CODCAT4" IS 'Codigo de categoria4';
   COMMENT ON COLUMN "BVTCAT4"."DESCAT4" IS 'Descripción categoria 4';
   COMMENT ON COLUMN "BVTCAT4"."USRCRE" IS 'Usuario de creacion de registro';
   COMMENT ON COLUMN "BVTCAT4"."FECCRE" IS 'Fecha de creacion de registro';
   COMMENT ON COLUMN "BVTCAT4"."USRACT" IS 'Usuario de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT4"."FECACT" IS 'Fecha de actualizacion de registro';
   COMMENT ON COLUMN "BVTCAT4"."EQUICAT4" IS 'EQUIVALENCIA DE CATEGORIA4';
   COMMENT ON COLUMN "BVTCAT4"."TIPPRO" IS 'Tipo de proceso ''P'' Aves en produccion, ''C'' Aves en cria';
   COMMENT ON COLUMN "BVTCAT4"."INSTANCIA" IS 'Instancia de usuario';
   COMMENT ON TABLE "BVTCAT4"  IS 'Categoria4';
--------------------------------------------------------
--  DDL for Table BVTMENU
--------------------------------------------------------

  CREATE TABLE "BVTMENU" 
   (	"B_CODROL" VARCHAR2(5 BYTE), 
	"CODOPC" VARCHAR2(4 BYTE), 
	"DESOPC" VARCHAR2(50 BYTE), 
	"CODVIS" VARCHAR2(1 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE, 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTMENU"."B_CODROL" IS 'USUARIO ACCESO AL MENU';
   COMMENT ON COLUMN "BVTMENU"."CODOPC" IS 'NUMERO DE OPCION';
   COMMENT ON COLUMN "BVTMENU"."DESOPC" IS 'DESCRIPCION DE OPCION';
   COMMENT ON COLUMN "BVTMENU"."CODVIS" IS 'VISUALIZA 1, NO VISUALIZA 0';
   COMMENT ON COLUMN "BVTMENU"."USRCRE" IS 'USUARIO DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVTMENU"."FECCRE" IS 'FECHA DE CREACION DE REGISTRO';
   COMMENT ON COLUMN "BVTMENU"."USRACT" IS 'USUARIO DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVTMENU"."FECACT" IS 'FECHA DE ACTUALIZACION DE REGISTRO';
   COMMENT ON COLUMN "BVTMENU"."INSTANCIA" IS 'ISTANCIA USUARIO';
   COMMENT ON TABLE "BVTMENU"  IS 'MENU';
--------------------------------------------------------
--  DDL for Table BVTPARAMS_NUMBER_TEMP
--------------------------------------------------------

  CREATE TABLE "BVTPARAMS_NUMBER_TEMP" 
   (	"CODREP" VARCHAR2(10 BYTE), 
	"SESSIONID" VARCHAR2(500 BYTE), 
	"PARAMNUMBER" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "BVTPARAMS_NUMBER_TEMP"."CODREP" IS 'Código del reporte';
   COMMENT ON COLUMN "BVTPARAMS_NUMBER_TEMP"."SESSIONID" IS 'Id de la sessión';
   COMMENT ON COLUMN "BVTPARAMS_NUMBER_TEMP"."PARAMNUMBER" IS 'Número de parámetros';
--------------------------------------------------------
--  DDL for Table BVTPARAMS_TEMP
--------------------------------------------------------

  CREATE TABLE "BVTPARAMS_TEMP" 
   (	"CODREP" VARCHAR2(10 BYTE), 
	"PARAMNAME" VARCHAR2(150 BYTE), 
	"PARAMTYPE" NUMBER(*,0), 
	"SESSIONID" VARCHAR2(500 BYTE), 
	"PROMPTEXT" VARCHAR2(150 BYTE)
   ) ;

   COMMENT ON COLUMN "BVTPARAMS_TEMP"."CODREP" IS 'Código del reporte';
   COMMENT ON COLUMN "BVTPARAMS_TEMP"."PARAMNAME" IS 'Nombre del parámetro';
   COMMENT ON COLUMN "BVTPARAMS_TEMP"."PARAMTYPE" IS 'Tipo del parámetro';
   COMMENT ON COLUMN "BVTPARAMS_TEMP"."SESSIONID" IS 'Id de la sessión';
   COMMENT ON COLUMN "BVTPARAMS_TEMP"."PROMPTEXT" IS 'Descripción del parámetro';
   COMMENT ON TABLE "BVTPARAMS_TEMP"  IS 'Temporal de parametros de reportes';
--------------------------------------------------------
--  DDL for Table INSTANCIAS
--------------------------------------------------------

  CREATE TABLE "INSTANCIAS" 
   (	"INSTANCIA" NUMBER(*,0), 
	"DESCRIPCION" VARCHAR2(50 BYTE), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE
   ) ;

   COMMENT ON COLUMN "INSTANCIAS"."INSTANCIA" IS 'Código';
   COMMENT ON COLUMN "INSTANCIAS"."DESCRIPCION" IS 'Descripción';
   COMMENT ON COLUMN "INSTANCIAS"."USRCRE" IS 'Usuario de creación de registro';
   COMMENT ON COLUMN "INSTANCIAS"."FECCRE" IS 'Fecha de creación de registro';
   COMMENT ON COLUMN "INSTANCIAS"."USRACT" IS 'Usuario de actualización de registro';
   COMMENT ON COLUMN "INSTANCIAS"."FECACT" IS 'Fecha de actualización de registro';
   COMMENT ON TABLE "INSTANCIAS"  IS 'Instancias';
--------------------------------------------------------
--  DDL for Table INSTANCIAS_USR
--------------------------------------------------------

  CREATE TABLE "INSTANCIAS_USR" 
   (	"CODUSER" VARCHAR2(10 BYTE), 
	"INSTANCIA" NUMBER(*,0), 
	"USRCRE" VARCHAR2(10 BYTE), 
	"FECCRE" DATE, 
	"USRACT" VARCHAR2(10 BYTE), 
	"FECACT" DATE
   ) ;

   COMMENT ON COLUMN "INSTANCIAS_USR"."CODUSER" IS 'Código de usuario';
   COMMENT ON COLUMN "INSTANCIAS_USR"."INSTANCIA" IS 'Instancias';
   COMMENT ON COLUMN "INSTANCIAS_USR"."USRCRE" IS 'Usuario de creación de registro';
   COMMENT ON COLUMN "INSTANCIAS_USR"."FECCRE" IS 'Fecha de creación de registro';
   COMMENT ON COLUMN "INSTANCIAS_USR"."USRACT" IS 'Usuario de actualización de registro';
   COMMENT ON COLUMN "INSTANCIAS_USR"."FECACT" IS 'Fecha de actualización de registro';
   COMMENT ON TABLE "INSTANCIAS_USR"  IS 'Asociar instancias a usuarios';
--------------------------------------------------------
--  DDL for Table MAILGRUPOS
--------------------------------------------------------

  CREATE TABLE "MAILGRUPOS" 
   (	"IDGRUPO" NUMBER(*,0), 
	"DESGRUPO" VARCHAR2(50 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "MAILGRUPOS"."IDGRUPO" IS 'ID DE GRUPO';
   COMMENT ON COLUMN "MAILGRUPOS"."DESGRUPO" IS 'DESCRIPCION DE GRUPO';
   COMMENT ON COLUMN "MAILGRUPOS"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "MAILGRUPOS"  IS 'ASOCIACION DE GRUPOS PARA CORREOS';
--------------------------------------------------------
--  DDL for Table MAILLISTA
--------------------------------------------------------

  CREATE TABLE "MAILLISTA" 
   (	"IDGRUPO" NUMBER(*,0), 
	"IDMAIL" VARCHAR2(50 BYTE), 
	"MAIL" VARCHAR2(50 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "MAILLISTA"."IDGRUPO" IS 'ID DEL GRUPO';
   COMMENT ON COLUMN "MAILLISTA"."IDMAIL" IS 'ID DEL MAIL';
   COMMENT ON COLUMN "MAILLISTA"."MAIL" IS 'DIRECCION DE CORREOS A ENVIAR';
   COMMENT ON COLUMN "MAILLISTA"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
   COMMENT ON TABLE "MAILLISTA"  IS 'LISTA DE CORREOS A ENVIAR POR GRUPO';
--------------------------------------------------------
--  DDL for Table T_PROGRAMACION
--------------------------------------------------------

  CREATE TABLE "T_PROGRAMACION" 
   (	"DISPARADOR" VARCHAR2(20 BYTE), 
	"GRUPO" VARCHAR2(20 BYTE), 
	"DIASEM" NUMBER(*,0), 
	"HORA" VARCHAR2(5 BYTE), 
	"FRECUENCIA" VARCHAR2(1 BYTE), 
	"ASUNTO" VARCHAR2(50 BYTE), 
	"CONTENIDO" VARCHAR2(4000 BYTE), 
	"CODREP" VARCHAR2(10 BYTE), 
	"RUTAREP" VARCHAR2(1500 BYTE), 
	"RUTATEMP" VARCHAR2(1500 BYTE), 
	"IDGRUPO" NUMBER(*,0), 
	"JOB" VARCHAR2(20 BYTE), 
	"DIAMES" VARCHAR2(2 BYTE), 
	"DIAINICIO" DATE, 
	"ACTIVA" VARCHAR2(20 BYTE), 
	"PARAMVALUES" VARCHAR2(500 BYTE), 
	"INTERVALO" NUMBER(*,0), 
	"PARAMNAMES" VARCHAR2(500 BYTE), 
	"RUTA_SALIDA" VARCHAR2(500 BYTE), 
	"OPCTAREAS" VARCHAR2(1 BYTE), 
	"FORMATO" VARCHAR2(4 BYTE), 
	"INSTANCIA" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "T_PROGRAMACION"."DISPARADOR" IS 'NOMBRE DEL DISPARADOR';
   COMMENT ON COLUMN "T_PROGRAMACION"."GRUPO" IS 'NOMBRE DEL GRUPO DE LA TAREA';
   COMMENT ON COLUMN "T_PROGRAMACION"."DIASEM" IS 'DIA DE LA SEMANA';
   COMMENT ON COLUMN "T_PROGRAMACION"."HORA" IS 'HORA DE EJECUCION hh:mm';
   COMMENT ON COLUMN "T_PROGRAMACION"."FRECUENCIA" IS 'FRECUENCIA DE EJECUCION, 0-Diaria, 1-Semanal, 2-Personalizada, 3-Mensual, 4-BIZVIEWmensual, 5-Trimestral';
   COMMENT ON COLUMN "T_PROGRAMACION"."ASUNTO" IS 'ASUNTO DEL CORREO';
   COMMENT ON COLUMN "T_PROGRAMACION"."CONTENIDO" IS 'CONTENIDO DEL CORREO';
   COMMENT ON COLUMN "T_PROGRAMACION"."CODREP" IS 'CODIGO DEL REPORTE';
   COMMENT ON COLUMN "T_PROGRAMACION"."RUTAREP" IS 'RUTA DE UBUCACION DEL REPORTE';
   COMMENT ON COLUMN "T_PROGRAMACION"."RUTATEMP" IS 'RUTA DE GENERACION TEMPORAL DEL REPORTE';
   COMMENT ON COLUMN "T_PROGRAMACION"."IDGRUPO" IS 'ID GRUPO LISTA DE CORREOS ';
   COMMENT ON COLUMN "T_PROGRAMACION"."JOB" IS 'NOMBRE DE LA TAREA O JOB';
   COMMENT ON COLUMN "T_PROGRAMACION"."DIAMES" IS 'DIA DEL MES PARA ENVIOS PERSONALIZADOS';
   COMMENT ON COLUMN "T_PROGRAMACION"."DIAINICIO" IS 'DIA DE INICIO PARA INTERVALOS MENSUALES';
   COMMENT ON COLUMN "T_PROGRAMACION"."ACTIVA" IS 'INDICA SI LA TAREA ESTÁ ACTIVA O NO. 0-ACTIVA, 1-INACTIVA';
   COMMENT ON COLUMN "T_PROGRAMACION"."PARAMVALUES" IS 'VALORES DE LOS PARAMETROS';
   COMMENT ON COLUMN "T_PROGRAMACION"."INTERVALO" IS 'REPETICION CUANDO ES POR HORA';
   COMMENT ON COLUMN "T_PROGRAMACION"."PARAMNAMES" IS 'NOMBRE DE LOS PARAMETROS DE REPORTE';
   COMMENT ON COLUMN "T_PROGRAMACION"."RUTA_SALIDA" IS 'RUTA URL DE SALIDA DE REPORTE';
   COMMENT ON COLUMN "T_PROGRAMACION"."OPCTAREAS" IS 'OPCIONES DE TAREAS: 1-ENVÍA REPORTES A LISTA DE CORREO, 2-ENVÍA REPORTES A RUTA, 3-ENVÍA REPORTES A LISTA PERSONALIZADA';
   COMMENT ON COLUMN "T_PROGRAMACION"."FORMATO" IS 'FORMATO DE ENVIO PDF, XLS, XLSX, ODS';
   COMMENT ON COLUMN "T_PROGRAMACION"."INSTANCIA" IS 'INSTANCIA DE USUARIO';
--------------------------------------------------------
--  DDL for Index ACCCAT1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT1_PK" ON "ACCCAT1" ("B_CODROL", "B_CODCAT1") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT1_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT1_PK1" ON "ACCCAT1" ("B_CODROL", "B_CODCAT1", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT4_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT4_PK" ON "ACCCAT4" ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "B_CODCAT4", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT001A_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT001A_PK1" ON "BVT001A" ("CODGRUP", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT001A_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT001A_PK" ON "BVT001A" ("CODGRUP") 
  ;
--------------------------------------------------------
--  DDL for Index BVT003A_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT003A_PK" ON "BVT003A" ("CODROL", "CODUSER", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT003_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT003_PK1" ON "BVT003" ("CODROL", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT003_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT003_PK" ON "BVT003" ("CODROL") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT2_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT2_PK" ON "ACCCAT2" ("B_CODROL", "B_CODCAT1", "B_CODCAT2") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT2_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT2_PK1" ON "ACCCAT2" ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT002_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT002_PK" ON "BVT002" ("CODUSER") 
  ;
--------------------------------------------------------
--  DDL for Index BVT001_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT001_PK" ON "BVT001" ("CODREP") 
  ;
--------------------------------------------------------
--  DDL for Index BVT001_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT001_PK1" ON "BVT001" ("CODREP", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT3_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT3_PK" ON "ACCCAT3" ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "B_CODCAT3") 
  ;
--------------------------------------------------------
--  DDL for Index ACCCAT3_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "ACCCAT3_PK1" ON "ACCCAT3" ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT005_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT005_PK1" ON "BVT005" ("B_CODROL", "CODOPC", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT005_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT005_PK" ON "BVT005" ("B_CODROL", "CODOPC") 
  ;
--------------------------------------------------------
--  DDL for Index BVT006_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT006_PK1" ON "BVT006" ("B_CODREP", "B_CODUSER", "FECACC", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT006_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT006_PK" ON "BVT006" ("B_CODREP", "B_DESREP", "B_CODUSER", "FECACC") 
  ;
--------------------------------------------------------
--  DDL for Index BVT007_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT007_PK1" ON "BVT007" ("B_CODROL", "B_CODREP", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVT007_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVT007_PK" ON "BVT007" ("B_CODROL", "B_CODREP") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT1_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT1_PK1" ON "BVTCAT1" ("CODCAT1", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT1_PK" ON "BVTCAT1" ("CODCAT1") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT2_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT2_PK1" ON "BVTCAT2" ("B_CODCAT1", "CODCAT2", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT2_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT2_PK" ON "BVTCAT2" ("B_CODCAT1", "CODCAT2") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT3_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT3_PK" ON "BVTCAT3" ("B_CODCAT1", "B_CODCAT2", "CODCAT3") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT3_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT3_PK1" ON "BVTCAT3" ("B_CODCAT1", "B_CODCAT2", "CODCAT3", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT4_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT4_PK1" ON "BVTCAT4" ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA", "CODCAT4") 
  ;
--------------------------------------------------------
--  DDL for Index BVTCAT4_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTCAT4_PK" ON "BVTCAT4" ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "CODCAT4") 
  ;
--------------------------------------------------------
--  DDL for Index BVTMENU_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTMENU_PK1" ON "BVTMENU" ("B_CODROL", "CODOPC", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index BVTMENU_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTMENU_PK" ON "BVTMENU" ("B_CODROL", "CODOPC") 
  ;
--------------------------------------------------------
--  DDL for Index BVTPARAMS_NUMBER_TEMP_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTPARAMS_NUMBER_TEMP_PK" ON "BVTPARAMS_NUMBER_TEMP" ("CODREP", "SESSIONID") 
  ;
--------------------------------------------------------
--  DDL for Index BVTPARAMS_TEMP_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BVTPARAMS_TEMP_PK" ON "BVTPARAMS_TEMP" ("CODREP", "PARAMNAME", "SESSIONID") 
  ;
--------------------------------------------------------
--  DDL for Index INSTANCIAS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "INSTANCIAS_PK" ON "INSTANCIAS" ("INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index INSTANCIAS_USR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "INSTANCIAS_USR_PK" ON "INSTANCIAS_USR" ("CODUSER", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index MAILGRUPOS_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "MAILGRUPOS_PK1" ON "MAILGRUPOS" ("IDGRUPO", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index MAILGRUPOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MAILGRUPOS_PK" ON "MAILGRUPOS" ("IDGRUPO") 
  ;
--------------------------------------------------------
--  DDL for Index MAILLISTA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MAILLISTA_PK" ON "MAILLISTA" ("IDGRUPO", "IDMAIL") 
  ;
--------------------------------------------------------
--  DDL for Index MAILLISTA_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "MAILLISTA_PK1" ON "MAILLISTA" ("IDGRUPO", "IDMAIL", "INSTANCIA") 
  ;
--------------------------------------------------------
--  DDL for Index T_PROGRAMACION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_PROGRAMACION_PK" ON "T_PROGRAMACION" ("DISPARADOR", "JOB") 
  ;
--------------------------------------------------------
--  DDL for Index T_PROGRAMACION_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_PROGRAMACION_PK1" ON "T_PROGRAMACION" ("DISPARADOR", "JOB", "INSTANCIA") 
  ;
--------------------------------------------------------
--  Constraints for Table ACCCAT1
--------------------------------------------------------

  ALTER TABLE "ACCCAT1" ADD CONSTRAINT "ACCCAT1_PK" PRIMARY KEY ("B_CODROL", "B_CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT1" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT1" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT1" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BIAUDIT
--------------------------------------------------------

  ALTER TABLE "BIAUDIT" MODIFY ("FECHADIA" NOT NULL ENABLE);
  ALTER TABLE "BIAUDIT" MODIFY ("RESULT" NOT NULL ENABLE);
  ALTER TABLE "BIAUDIT" MODIFY ("DETFAZ" NOT NULL ENABLE);
  ALTER TABLE "BIAUDIT" MODIFY ("FECACC" NOT NULL ENABLE);
  ALTER TABLE "BIAUDIT" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ACCCAT4
--------------------------------------------------------

  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_PK" PRIMARY KEY ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "B_CODCAT4", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT4" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT4" MODIFY ("B_CODCAT4" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT4" MODIFY ("B_CODCAT3" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT4" MODIFY ("B_CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT4" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT4" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT001A
--------------------------------------------------------

  ALTER TABLE "BVT001A" MODIFY ("DESGRUP" NOT NULL ENABLE);
  ALTER TABLE "BVT001A" MODIFY ("CODGRUP" NOT NULL ENABLE);
  ALTER TABLE "BVT001A" ADD CONSTRAINT "BVT001A_PK" PRIMARY KEY ("CODGRUP", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT001A" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT003A
--------------------------------------------------------

  ALTER TABLE "BVT003A" ADD CONSTRAINT "BVT003A_PK" PRIMARY KEY ("CODROL", "CODUSER", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT003A" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT003A" MODIFY ("CODUSER" NOT NULL ENABLE);
  ALTER TABLE "BVT003A" MODIFY ("CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT003
--------------------------------------------------------

  ALTER TABLE "BVT003" ADD CONSTRAINT "BVT003_PK" PRIMARY KEY ("CODROL", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT003" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT003" MODIFY ("DESROL" NOT NULL ENABLE);
  ALTER TABLE "BVT003" MODIFY ("CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ACCCAT2
--------------------------------------------------------

  ALTER TABLE "ACCCAT2" ADD CONSTRAINT "ACCCAT2_PK" PRIMARY KEY ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT2" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT2" MODIFY ("B_CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT2" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT2" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT002
--------------------------------------------------------

  ALTER TABLE "BVT002" MODIFY ("MAIL" NOT NULL ENABLE);
  ALTER TABLE "BVT002" ADD CONSTRAINT "BVT002_PK" PRIMARY KEY ("CODUSER") ENABLE;
  ALTER TABLE "BVT002" MODIFY ("B_CODROL" NOT NULL ENABLE);
  ALTER TABLE "BVT002" MODIFY ("CLUSER" NOT NULL ENABLE);
  ALTER TABLE "BVT002" MODIFY ("DESUSER" NOT NULL ENABLE);
  ALTER TABLE "BVT002" MODIFY ("CODUSER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT001
--------------------------------------------------------

  ALTER TABLE "BVT001" MODIFY ("COMREP" NOT NULL ENABLE);
  ALTER TABLE "BVT001" MODIFY ("DESREP" NOT NULL ENABLE);
  ALTER TABLE "BVT001" MODIFY ("CODREP" NOT NULL ENABLE);
  ALTER TABLE "BVT001" ADD CONSTRAINT "BVT001_PK" PRIMARY KEY ("CODREP", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT001" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ACCCAT3
--------------------------------------------------------

  ALTER TABLE "ACCCAT3" MODIFY ("B_CODCAT3" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT3" MODIFY ("B_CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT3" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT3" MODIFY ("B_CODROL" NOT NULL ENABLE);
  ALTER TABLE "ACCCAT3" ADD CONSTRAINT "ACCCAT3_PK" PRIMARY KEY ("B_CODROL", "B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT3" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT005
--------------------------------------------------------

  ALTER TABLE "BVT005" ADD CONSTRAINT "BVT005_PK" PRIMARY KEY ("B_CODROL", "CODOPC", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT005" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT005" MODIFY ("CODVIS" NOT NULL ENABLE);
  ALTER TABLE "BVT005" MODIFY ("DESOPC" NOT NULL ENABLE);
  ALTER TABLE "BVT005" MODIFY ("CODOPC" NOT NULL ENABLE);
  ALTER TABLE "BVT005" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT006
--------------------------------------------------------

  ALTER TABLE "BVT006" ADD CONSTRAINT "BVT006_PK" PRIMARY KEY ("B_CODREP", "B_CODUSER", "FECACC", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT006" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT006" MODIFY ("FECACC" NOT NULL ENABLE);
  ALTER TABLE "BVT006" MODIFY ("B_CODUSER" NOT NULL ENABLE);
  ALTER TABLE "BVT006" MODIFY ("B_DESREP" NOT NULL ENABLE);
  ALTER TABLE "BVT006" MODIFY ("B_CODREP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVT007
--------------------------------------------------------

  ALTER TABLE "BVT007" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVT007" ADD CONSTRAINT "BVT007_PK" PRIMARY KEY ("B_CODROL", "B_CODREP", "INSTANCIA") ENABLE;
  ALTER TABLE "BVT007" MODIFY ("B_CODREP" NOT NULL ENABLE);
  ALTER TABLE "BVT007" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTCAT1
--------------------------------------------------------

  ALTER TABLE "BVTCAT1" MODIFY ("DESCAT1" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT1" MODIFY ("CODCAT1" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT1" ADD CONSTRAINT "BVTCAT1_PK" PRIMARY KEY ("CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "BVTCAT1" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTCAT2
--------------------------------------------------------

  ALTER TABLE "BVTCAT2" ADD CONSTRAINT "BVTCAT2_PK" PRIMARY KEY ("B_CODCAT1", "CODCAT2", "INSTANCIA") ENABLE;
  ALTER TABLE "BVTCAT2" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT2" MODIFY ("DESCAT2" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT2" MODIFY ("CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT2" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTCAT3
--------------------------------------------------------

  ALTER TABLE "BVTCAT3" ADD CONSTRAINT "BVTCAT3_PK" PRIMARY KEY ("B_CODCAT1", "B_CODCAT2", "CODCAT3", "INSTANCIA") ENABLE;
  ALTER TABLE "BVTCAT3" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT3" MODIFY ("DESCAT3" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT3" MODIFY ("CODCAT3" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT3" MODIFY ("B_CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT3" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTCAT4
--------------------------------------------------------

  ALTER TABLE "BVTCAT4" ADD CONSTRAINT "BVTCAT4_PK" PRIMARY KEY ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA", "CODCAT4") ENABLE;
  ALTER TABLE "BVTCAT4" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT4" MODIFY ("DESCAT4" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT4" MODIFY ("CODCAT4" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT4" MODIFY ("B_CODCAT3" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT4" MODIFY ("B_CODCAT2" NOT NULL ENABLE);
  ALTER TABLE "BVTCAT4" MODIFY ("B_CODCAT1" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTMENU
--------------------------------------------------------

  ALTER TABLE "BVTMENU" ADD CONSTRAINT "BVTMENU_PK" PRIMARY KEY ("B_CODROL", "CODOPC", "INSTANCIA") ENABLE;
  ALTER TABLE "BVTMENU" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "BVTMENU" MODIFY ("CODVIS" NOT NULL ENABLE);
  ALTER TABLE "BVTMENU" MODIFY ("DESOPC" NOT NULL ENABLE);
  ALTER TABLE "BVTMENU" MODIFY ("CODOPC" NOT NULL ENABLE);
  ALTER TABLE "BVTMENU" MODIFY ("B_CODROL" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTPARAMS_NUMBER_TEMP
--------------------------------------------------------

  ALTER TABLE "BVTPARAMS_NUMBER_TEMP" ADD CONSTRAINT "BVTPARAMS_NUMBER_TEMP_PK" PRIMARY KEY ("CODREP", "SESSIONID") ENABLE;
  ALTER TABLE "BVTPARAMS_NUMBER_TEMP" MODIFY ("PARAMNUMBER" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_NUMBER_TEMP" MODIFY ("SESSIONID" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_NUMBER_TEMP" MODIFY ("CODREP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BVTPARAMS_TEMP
--------------------------------------------------------

  ALTER TABLE "BVTPARAMS_TEMP" MODIFY ("PROMPTEXT" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_TEMP" ADD CONSTRAINT "BVTPARAMS_TEMP_PK" PRIMARY KEY ("CODREP", "PARAMNAME", "SESSIONID") ENABLE;
  ALTER TABLE "BVTPARAMS_TEMP" MODIFY ("SESSIONID" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_TEMP" MODIFY ("PARAMTYPE" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_TEMP" MODIFY ("PARAMNAME" NOT NULL ENABLE);
  ALTER TABLE "BVTPARAMS_TEMP" MODIFY ("CODREP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INSTANCIAS
--------------------------------------------------------

  ALTER TABLE "INSTANCIAS" ADD CONSTRAINT "INSTANCIAS_CHK1" CHECK (instancia > 0) ENABLE;
  ALTER TABLE "INSTANCIAS" ADD CONSTRAINT "INSTANCIAS_PK" PRIMARY KEY ("INSTANCIA") ENABLE;
  ALTER TABLE "INSTANCIAS" MODIFY ("DESCRIPCION" NOT NULL ENABLE);
  ALTER TABLE "INSTANCIAS" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table INSTANCIAS_USR
--------------------------------------------------------

  ALTER TABLE "INSTANCIAS_USR" ADD CONSTRAINT "INSTANCIAS_USR_PK" PRIMARY KEY ("CODUSER", "INSTANCIA") ENABLE;
  ALTER TABLE "INSTANCIAS_USR" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "INSTANCIAS_USR" MODIFY ("CODUSER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MAILGRUPOS
--------------------------------------------------------

  ALTER TABLE "MAILGRUPOS" ADD CONSTRAINT "MAILGRUPOS_PK" PRIMARY KEY ("IDGRUPO", "INSTANCIA") ENABLE;
  ALTER TABLE "MAILGRUPOS" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "MAILGRUPOS" MODIFY ("DESGRUPO" NOT NULL ENABLE);
  ALTER TABLE "MAILGRUPOS" MODIFY ("IDGRUPO" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MAILLISTA
--------------------------------------------------------

  ALTER TABLE "MAILLISTA" MODIFY ("MAIL" NOT NULL ENABLE);
  ALTER TABLE "MAILLISTA" MODIFY ("IDMAIL" NOT NULL ENABLE);
  ALTER TABLE "MAILLISTA" MODIFY ("IDGRUPO" NOT NULL ENABLE);
  ALTER TABLE "MAILLISTA" ADD CONSTRAINT "MAILLISTA_PK" PRIMARY KEY ("IDGRUPO", "IDMAIL", "INSTANCIA") ENABLE;
  ALTER TABLE "MAILLISTA" MODIFY ("INSTANCIA" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table T_PROGRAMACION
--------------------------------------------------------

  ALTER TABLE "T_PROGRAMACION" ADD CONSTRAINT "T_PROGRAMACION_PK" PRIMARY KEY ("DISPARADOR", "JOB", "INSTANCIA") ENABLE;
  ALTER TABLE "T_PROGRAMACION" MODIFY ("INSTANCIA" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("FORMATO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("OPCTAREAS" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("INTERVALO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("ACTIVA" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("DIAINICIO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("DIAMES" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("JOB" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("IDGRUPO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("RUTATEMP" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("RUTAREP" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("CODREP" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("CONTENIDO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("ASUNTO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("FRECUENCIA" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("HORA" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("DIASEM" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("GRUPO" NOT NULL ENABLE);
  ALTER TABLE "T_PROGRAMACION" MODIFY ("DISPARADOR" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table ACCCAT1
--------------------------------------------------------

  ALTER TABLE "ACCCAT1" ADD CONSTRAINT "ACCCAT1_CAT1_FK" FOREIGN KEY ("B_CODCAT1", "INSTANCIA")
	  REFERENCES "BVTCAT1" ("CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT1" ADD CONSTRAINT "ACCCAT1_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BIAUDIT
--------------------------------------------------------

  ALTER TABLE "BIAUDIT" ADD CONSTRAINT "BIAUDIT_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACCCAT4
--------------------------------------------------------

  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_CAT1_FK" FOREIGN KEY ("B_CODCAT1", "INSTANCIA")
	  REFERENCES "BVTCAT1" ("CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_CAT2_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "INSTANCIA")
	  REFERENCES "BVTCAT2" ("B_CODCAT1", "CODCAT2", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_CAT3_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA")
	  REFERENCES "BVTCAT3" ("B_CODCAT1", "B_CODCAT2", "CODCAT3", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_CAT4_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA", "B_CODCAT4")
	  REFERENCES "BVTCAT4" ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA", "CODCAT4") ENABLE;
  ALTER TABLE "ACCCAT4" ADD CONSTRAINT "ACCCAT4_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT001A
--------------------------------------------------------

  ALTER TABLE "BVT001A" ADD CONSTRAINT "BVT001A_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT003A
--------------------------------------------------------

  ALTER TABLE "BVT003A" ADD CONSTRAINT "BVT003A_CODUSER_FK" FOREIGN KEY ("CODUSER")
	  REFERENCES "BVT002" ("CODUSER") ENABLE;
  ALTER TABLE "BVT003A" ADD CONSTRAINT "BVT003A_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "BVT003A" ADD CONSTRAINT "BVT003A_ROL_FK" FOREIGN KEY ("CODROL", "INSTANCIA")
	  REFERENCES "BVT003" ("CODROL", "INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT003
--------------------------------------------------------

  ALTER TABLE "BVT003" ADD CONSTRAINT "BVT003_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACCCAT2
--------------------------------------------------------

  ALTER TABLE "ACCCAT2" ADD CONSTRAINT "ACCCAT2_CAT1_FK" FOREIGN KEY ("B_CODCAT1", "INSTANCIA")
	  REFERENCES "BVTCAT1" ("CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT2" ADD CONSTRAINT "ACCCAT2_CAT2_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "INSTANCIA")
	  REFERENCES "BVTCAT2" ("B_CODCAT1", "CODCAT2", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT2" ADD CONSTRAINT "ACCCAT2_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT001
--------------------------------------------------------

  ALTER TABLE "BVT001" ADD CONSTRAINT "BVT001_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACCCAT3
--------------------------------------------------------

  ALTER TABLE "ACCCAT3" ADD CONSTRAINT "ACCCAT3_CAT1_FK" FOREIGN KEY ("B_CODCAT1", "INSTANCIA")
	  REFERENCES "BVTCAT1" ("CODCAT1", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT3" ADD CONSTRAINT "ACCCAT3_CAT2_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "INSTANCIA")
	  REFERENCES "BVTCAT2" ("B_CODCAT1", "CODCAT2", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT3" ADD CONSTRAINT "ACCCAT3_CAT3_FK" FOREIGN KEY ("B_CODCAT1", "B_CODCAT2", "B_CODCAT3", "INSTANCIA")
	  REFERENCES "BVTCAT3" ("B_CODCAT1", "B_CODCAT2", "CODCAT3", "INSTANCIA") ENABLE;
  ALTER TABLE "ACCCAT3" ADD CONSTRAINT "ACCCAT3_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT005
--------------------------------------------------------

  ALTER TABLE "BVT005" ADD CONSTRAINT "BVT005_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "BVT005" ADD CONSTRAINT "BVT005_ROL_FK" FOREIGN KEY ("B_CODROL", "INSTANCIA")
	  REFERENCES "BVT003" ("CODROL", "INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT006
--------------------------------------------------------

  ALTER TABLE "BVT006" ADD CONSTRAINT "BVT006_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVT007
--------------------------------------------------------

  ALTER TABLE "BVT007" ADD CONSTRAINT "BVT007_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "BVT007" ADD CONSTRAINT "BVT007_ROL_FK" FOREIGN KEY ("B_CODROL", "INSTANCIA")
	  REFERENCES "BVT003" ("CODROL", "INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVTCAT1
--------------------------------------------------------

  ALTER TABLE "BVTCAT1" ADD CONSTRAINT "BVTCAT1_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVTCAT2
--------------------------------------------------------

  ALTER TABLE "BVTCAT2" ADD CONSTRAINT "BVTCAT2_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVTCAT3
--------------------------------------------------------

  ALTER TABLE "BVTCAT3" ADD CONSTRAINT "BVTCAT3_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVTCAT4
--------------------------------------------------------

  ALTER TABLE "BVTCAT4" ADD CONSTRAINT "BVTCAT4_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BVTMENU
--------------------------------------------------------

  ALTER TABLE "BVTMENU" ADD CONSTRAINT "BVTMENU_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "BVTMENU" ADD CONSTRAINT "BVTMENU_ROL_FK" FOREIGN KEY ("B_CODROL", "INSTANCIA")
	  REFERENCES "BVT003" ("CODROL", "INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table INSTANCIAS_USR
--------------------------------------------------------

  ALTER TABLE "INSTANCIAS_USR" ADD CONSTRAINT "INSTANCIAS_USR_FK1" FOREIGN KEY ("CODUSER")
	  REFERENCES "BVT002" ("CODUSER") ENABLE;
  ALTER TABLE "INSTANCIAS_USR" ADD CONSTRAINT "INSTANCIAS_USR_FK2" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MAILGRUPOS
--------------------------------------------------------

  ALTER TABLE "MAILGRUPOS" ADD CONSTRAINT "MAILGRUPOS_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MAILLISTA
--------------------------------------------------------

  ALTER TABLE "MAILLISTA" ADD CONSTRAINT "MAILLISTA_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "MAILLISTA" ADD CONSTRAINT "MAILLISTA_IDGRUPO_FK" FOREIGN KEY ("IDGRUPO", "IDMAIL", "INSTANCIA")
	  REFERENCES "MAILLISTA" ("IDGRUPO", "IDMAIL", "INSTANCIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table T_PROGRAMACION
--------------------------------------------------------

  ALTER TABLE "T_PROGRAMACION" ADD CONSTRAINT "T_PROGRAMACION_FK1" FOREIGN KEY ("INSTANCIA")
	  REFERENCES "INSTANCIAS" ("INSTANCIA") ENABLE;
  ALTER TABLE "T_PROGRAMACION" ADD CONSTRAINT "T_PROGRAMACION_IDGRUPO_FK" FOREIGN KEY ("IDGRUPO", "INSTANCIA")
	  REFERENCES "MAILGRUPOS" ("IDGRUPO", "INSTANCIA") ENABLE;
  ALTER TABLE "T_PROGRAMACION" ADD CONSTRAINT "T_PROGRAMACION_REP_FK" FOREIGN KEY ("CODREP", "INSTANCIA")
	  REFERENCES "BVT001" ("CODREP", "INSTANCIA") ENABLE;
