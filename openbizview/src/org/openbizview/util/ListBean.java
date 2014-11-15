	package org.openbizview.util;
	
	import javax.faces.bean.ManagedBean;
	import javax.faces.bean.RequestScoped;
	import javax.faces.model.SelectItem;
	
	
	@ManagedBean
	@RequestScoped
	public class ListBean extends Bd {
	
	
	       
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE PAGINACION///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			
			
			//Selecciona las opciones de paginación de todas las tablas
			private SelectItem[] rpp = new SelectItem[]{
				    new SelectItem("5", getMessage("filas") +  " 5"),
			        new SelectItem("10", getMessage("filas") + " 10"),
			        new SelectItem("15", getMessage("filas") + " 15"),
			        new SelectItem("20", getMessage("filas") + " 20"),
			        new SelectItem("50", getMessage("filas") + " 50"),
			        new SelectItem("100", getMessage("filas") + " 100"),
			        new SelectItem("500", getMessage("filas") + " 500")};
			
			private String rppSelected = "15";
	
			/**
			 * @return the rpp
			 */
			public SelectItem[] getRpp() {
				return rpp;
			}
	
			/**
			 * @return the rppSelected
			 */
			public String getRppSelected() {
				return rppSelected;
			}
	
			/**
			 * @param rppSelected the rppSelected to set
			 */
			public void setRppSelected(String rppSelected) {
				this.rppSelected = rppSelected;
			}
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE EXTERNOS///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] opcext = new SelectItem[]{
	new SelectItem("1", getMessage("cat")),
	new SelectItem("bvtcat1", getMessage("cat1")),
	new SelectItem("bvtcat2", getMessage("cat2")),
	new SelectItem("bvtcat3", getMessage("cat3")),
	new SelectItem("bvtcat4", getMessage("cat4")),
	new SelectItem("bvt003", getMessage("roles")),
	new SelectItem("bvt002", getMessage("usr")),
	new SelectItem("acccat1", getMessage("acccat1")),
	new SelectItem("acccat2", getMessage("acccat2")),
	new SelectItem("acccat3", getMessage("acccat3")),
	new SelectItem("acccat4", getMessage("acccat4")),
	new SelectItem("bvt008", getMessage("plan")),
	new SelectItem("bvt014", getMessage("asodim")),
	new SelectItem("bvm001", getMessage("movcon")),
	new SelectItem("bvtreljer", getMessage("reljer"))};
	
	private String opcextselected = getMessage("cat");
	
	/**
	 * @return the opcextselected
	 */
	public String getOpcextselected() {
		return opcextselected;
	}
	
	/**
	 * @param opcextselected the opcextselected to set
	 */
	public void setOpcextselected(String opcextselected) {
		this.opcextselected = opcextselected;
	}
	
	/**
	 * @return the opcext
	 */
	public SelectItem[] getOpcext() {
		return opcext;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE LOG///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] opclog = new SelectItem[]{
	new SelectItem(" ", getMessage("biauditNegocio")),
	new SelectItem("DER", getMessage("biauditNegocio")),
	new SelectItem("PRE", getMessage("biauditNegocio")),
	new SelectItem("REPRO", getMessage("biauditNegocio")),
	new SelectItem("INCUB", getMessage("biauditNegocio")),
	new SelectItem("NOM", getMessage("biauditNegocio"))};
	
	private String opclogselected = getMessage("biauditNegocio");
	
	/**
	 * @return the opclogselected
	 */
	public String getOpclogselected() {
		return opclogselected;
	}
	
	/**
	 * @param opclogselected the opclogselected to set
	 */
	public void setOpclogselected(String opclogselected) {
		this.opclogselected = opclogselected;
	}
	
	/**
	 * @return the opclog
	 */
	public SelectItem[] getOpclog() {
		return opclog;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE PARAMETRIZACION///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] opcParam = new SelectItem[]{
	new SelectItem("0", getMessage("bvt015opc1")),
	new SelectItem("1", getMessage("bvt015opc10")),
	new SelectItem("3", getMessage("bvt015opc12"))};
	
	/**
	 * @return the opcParam
	 */
	public SelectItem[] getOpcParam() {
		return opcParam;
	}
	
	/**
	 * @param opcParam the opcParam to set
	 */
	public void setOpcParam(SelectItem[] opcParam) {
		this.opcParam = opcParam;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE PARAMETRIZACION///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] opcFactor = new SelectItem[]{
	new SelectItem("0", getMessage("bvt015opc5")),
	new SelectItem("1", getMessage("bvt015opc6"))};
	/**
	 * @return the opcFactor
	 */
	public SelectItem[] getOpcFactor() {
		return opcFactor;
	}
	
	/**
	 * @param opcFactor the opcFactor to set
	 */
	public void setOpcFactor(SelectItem[] opcFactor) {
		this.opcFactor = opcFactor;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE OPCIONES DE PARAMETRIZACION///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] estatus = new SelectItem[]{
	new SelectItem("0", getMessage("bvt016Cal")),
	new SelectItem("1", getMessage("bvt016Apro"))};
	
	private String estatusselected = getMessage("bvt016Cal");
	
	/**
	 * @return the estatus
	 */
	public SelectItem[] getEstatus() {
		return estatus;
	}
	
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(SelectItem[] estatus) {
		this.estatus = estatus;
	}
	
	/**
	 * @return the estatusselected
	 */
	public String getEstatusselected() {
		return estatusselected;
	}
	
	/**
	 * @param estatusselected the estatusselected to set
	 */
	public void setEstatusselected(String estatusselected) {
		this.estatusselected = estatusselected;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE PROYECCION DE AÑOS///////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] proyecta = new SelectItem[]{
	new SelectItem("1", getMessage("bvm002opc1")),
	new SelectItem("0", getMessage("bvm002opc2"))};
	
	private String proyectaselected = getMessage("bvm002opc2");
	
	/**
	 * @return the proyecta
	 */
	public SelectItem[] getProyecta() {
		return proyecta;
	}
	
	/**
	 * @param proyecta the proyecta to set
	 */
	public void setProyecta(SelectItem[] proyecta) {
		this.proyecta = proyecta;
	}
	
	/**
	 * @return the proyectaselected
	 */
	public String getProyectaselected() {
		return proyectaselected;
	}
	
	/**
	 * @param proyectaselected the proyectaselected to set
	 */
	public void setProyectaselected(String proyectaselected) {
		this.proyectaselected = proyectaselected;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE PORCENTAJE FACTOR////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] factoriza = new SelectItem[]{
	new SelectItem("2", getMessage("bvt015opc8")),
	new SelectItem("1", getMessage("bvt015opc7"))};
	
	private String factorizaaselected = getMessage("bvt015opc8");
	
	/**
	 * @return the factoriza
	 */
	public SelectItem[] getFactoriza() {
		return factoriza;
	}
	
	/**
	 * @param factoriza the factoriza to set
	 */
	public void setFactoriza(SelectItem[] factoriza) {
		this.factoriza = factoriza;
	}
	
	/**
	 * @return the factorizaaselected
	 */
	public String getFactorizaaselected() {
		return factorizaaselected;
	}
	
	/**
	 * @param factorizaaselected the factorizaaselected to set
	 */
	public void setFactorizaaselected(String factorizaaselected) {
		this.factorizaaselected = factorizaaselected;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////LISTA DE TAREAS////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] frecuencia = new SelectItem[]{
	new SelectItem("0", getMessage("mailtareaDiario")),
	new SelectItem("1", getMessage("mailtareaSemanal")),
	new SelectItem("2", getMessage("mailtareaPersonalizada")),
	new SelectItem("3", getMessage("mailtareaHoraRep")),
	//new SelectItem("3", getMessage("mailimes1")),
	//new SelectItem("4", getMessage("mailimes2")),
	new SelectItem("4", getMessage("mailimes1"))};
	
	/**
	 * @return the frecuencia
	 */
	public SelectItem[] getFrecuencia() {
		return frecuencia;
	}
	
	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(SelectItem[] frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////DIAS DE LA SEMANA////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] diassemana = new SelectItem[]{
	new SelectItem("2", getMessage("mailtarealunes")),
	new SelectItem("3", getMessage("mailtareamartes")),
	new SelectItem("4", getMessage("mailtareamiercoles")),
	new SelectItem("5", getMessage("mailtareajueves")),
	new SelectItem("6", getMessage("mailtareaviernes")),
	new SelectItem("7", getMessage("mailtareasabado")),
	new SelectItem("1", getMessage("mailtareadomingo"))};
	
	/**
	 * @return the diassemana
	 */
	public SelectItem[] getDiassemana() {
		return diassemana;
	}
	
	/**
	 * @param diassemana the diassemana to set
	 */
	public void setDiassemana(SelectItem[] diassemana) {
		this.diassemana = diassemana;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////DIAS DEL MES//////////////////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] diasmes = new SelectItem[]{
	new SelectItem("1", getMessage("mailidiap1")),
	new SelectItem("2", getMessage("mailidiap2")),
	new SelectItem("3", getMessage("mailidiap3")),
	new SelectItem("4", getMessage("mailidiap4")),
	new SelectItem("5", getMessage("mailidiap5")),
	new SelectItem("6", getMessage("mailidiap6")),
	new SelectItem("7", getMessage("mailidiap7")),
	new SelectItem("8", getMessage("mailidiap8")),
	new SelectItem("9", getMessage("mailidiap9")),
	new SelectItem("10", getMessage("mailidiap10")),
	new SelectItem("11", getMessage("mailidiap11")),
	new SelectItem("12", getMessage("mailidiap12")),
	new SelectItem("13", getMessage("mailidiap13")),
	new SelectItem("14", getMessage("mailidiap14")),
	new SelectItem("15", getMessage("mailidiap15")),
	new SelectItem("16", getMessage("mailidiap16")),
	new SelectItem("17", getMessage("mailidiap17")),
	new SelectItem("18", getMessage("mailidiap18")),
	new SelectItem("19", getMessage("mailidiap19")),
	new SelectItem("20", getMessage("mailidiap20")),
	new SelectItem("21", getMessage("mailidiap21")),
	new SelectItem("22", getMessage("mailidiap22")),
	new SelectItem("23", getMessage("mailidiap23")),
	new SelectItem("24", getMessage("mailidiap24")),
	new SelectItem("25", getMessage("mailidiap25")),
	new SelectItem("26", getMessage("mailidiap26")),
	new SelectItem("27", getMessage("mailidiap27")),
	new SelectItem("28", getMessage("mailidiap28")),
	new SelectItem("29", getMessage("mailidiap29")),
	new SelectItem("30", getMessage("mailidiap30"))};
	
	/**
	 * @return the diasmes
	 */
	public SelectItem[] getDiasmes() {
		return diasmes;
	}
	
	/**
	 * @param diasmes the diasmes to set
	 */
	public void setDiasmes(SelectItem[] diasmes) {
		this.diasmes = diasmes;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////TIPO DE AVES////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] tipopollo = new SelectItem[]{
	new SelectItem("INICIADOS", getMessage("pfm004TipAve1")),
	new SelectItem("TERMINADOS", getMessage("pfm004TipAve2"))};

	/**
	 * @return the tipopollo
	 */
	public SelectItem[] getTipopollo() {
		return tipopollo;
	}

	/**
	 * @param tipopollo the tipopollo to set
	 */
	public void setTipopollo(SelectItem[] tipopollo) {
		this.tipopollo = tipopollo;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////ZONAS/////////////////////////////////////////////////////////////////////		
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] zona = new SelectItem[]{
	new SelectItem("CENTRO", getMessage("pfm004Zona1")),
	new SelectItem("ORIENTE", getMessage("pfm004Zona2")),
	new SelectItem("OCCIDENTE", getMessage("pfm004Zona3"))};

	/**
	 * @return the zonas
	 */
	public SelectItem[] getZona() {
		return zona;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZona(SelectItem[] zona) {
		this.zona = zona;
	}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////lista de opciones de tareas envíos//////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Selecciona las opciones de paginación de todas las tablas
		private SelectItem[] opcTareas = new SelectItem[]{
		new SelectItem("1", getMessage("mailtareaopc1")),
		new SelectItem("2", getMessage("mailtareaopc2"))};

		/**
		 * @return the opcTareas
		 */
		public SelectItem[] getOpcTareas() {
			return opcTareas;
		}

		/**
		 * @param opcTareas the opcTareas to set
		 */
		public void setOpcTareas(SelectItem[] opcTareas) {
			this.opcTareas = opcTareas;
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////lista de opciones formato//////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//Selecciona las opciones de paginación de todas las tablas
	private SelectItem[] opcEmiters = new SelectItem[]{
	new SelectItem("pdf", getMessage("maltareaoutputPdf")),
	new SelectItem("xls", getMessage("maltareaoutputXls")),
	new SelectItem("xlsx", getMessage("maltareaoutputXlsx")),
	new SelectItem("ods", getMessage("maltareaoutputOds"))};

	/**
	 * @return the opcEmiters
	 */
	public SelectItem[] getOpcEmiters() {
		return opcEmiters;
	}

	/**
	 * @param opcEmiters the opcEmiters to set
	 */
	public void setOpcEmiters(SelectItem[] opcEmiters) {
		this.opcEmiters = opcEmiters;
	}
	
	
	
	}
