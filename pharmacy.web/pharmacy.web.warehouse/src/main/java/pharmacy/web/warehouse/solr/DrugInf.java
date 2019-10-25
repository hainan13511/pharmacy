package pharmacy.web.warehouse.solr;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class DrugInf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field("drugid")
	private int drugId;
	@Field("drug_name")
	private String drugName;
	@Field("chemical_name")
	private String chemicalName;
	@Field("commonly_name")
	private String commonlyName;
	@Field("specification")
	private String specification;
	@Field("dosage")
	private String dosage;
	@Field("formula")
	private String formula;
	@Field("method_users")
	private String methodUsers;
	@Field("markup")
	private String markup;
	@Field("spell")
	private String spell;
	@Field("five_stroke")
	private String fiveStroke;
	@Field("invoice_name")
	private String invoiceName;
	@Field("antibiotics")
	private int antibiotics;
	@Field("dose")
	private String dose;
	@Field("number_days")
	private String numberDays;
	@Field("dtid")
	private Integer dtId;
	@Field("dstate")
	private Integer dState;
	@Field("dcost")
	private int dcost;
	@Field("dsale")
	private int dsale;
	@Field("insurvalue")
	private String insurValue;

	public DrugInf(int drugId, String drugName, String chemicalName, String commonlyName, String specification,
			String dosage, String formula, String methodUsers, String markup, String spell, String fiveStroke,
			String invoiceName, int antibiotics, String dose, String numberDays, Integer dtId, Integer dState,
			int dcost, int dsale, String insurValue) {
		super();
		this.drugId = drugId;
		this.drugName = drugName;
		this.chemicalName = chemicalName;
		this.commonlyName = commonlyName;
		this.specification = specification;
		this.dosage = dosage;
		this.formula = formula;
		this.methodUsers = methodUsers;
		this.markup = markup;
		this.spell = spell;
		this.fiveStroke = fiveStroke;
		this.invoiceName = invoiceName;
		this.antibiotics = antibiotics;
		this.dose = dose;
		this.numberDays = numberDays;
		this.dtId = dtId;
		this.dState = dState;
		this.dcost = dcost;
		this.dsale = dsale;
		this.insurValue = insurValue;
	}
	public DrugInf() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	public String getCommonlyName() {
		return commonlyName;
	}
	public void setCommonlyName(String commonlyName) {
		this.commonlyName = commonlyName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getMethodUsers() {
		return methodUsers;
	}
	public void setMethodUsers(String methodUsers) {
		this.methodUsers = methodUsers;
	}
	public String getMarkup() {
		return markup;
	}
	public void setMarkup(String markup) {
		this.markup = markup;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public int getAntibiotics() {
		return antibiotics;
	}
	public void setAntibiotics(int antibiotics) {
		this.antibiotics = antibiotics;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getNumberDays() {
		return numberDays;
	}
	public void setNumberDays(String numberDays) {
		this.numberDays = numberDays;
	}
	public Integer getDtId() {
		return dtId;
	}
	public void setDtId(Integer dtId) {
		this.dtId = dtId;
	}
	public Integer getdState() {
		return dState;
	}
	public void setdState(Integer dState) {
		this.dState = dState;
	}
	public int getDcost() {
		return dcost;
	}
	public void setDcost(int dcost) {
		this.dcost = dcost;
	}
	public int getDsale() {
		return dsale;
	}
	public void setDsale(int dsale) {
		this.dsale = dsale;
	}
	public String getInsurValue() {
		return insurValue;
	}
	public void setInsurValue(String insurValue) {
		this.insurValue = insurValue;
	}
	

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(", drugId=");
        builder.append(drugId);
        builder.append(", drugName=");
        builder.append(drugName);
        builder.append(", chemicalName=");
        builder.append(chemicalName);
        builder.append(", commonlyName=");
        builder.append(commonlyName);
        builder.append(", specification=");
        builder.append(specification);
        builder.append(", dosage=");
        builder.append(dosage);
        builder.append(", formula=");
        builder.append(formula);
        builder.append(", methodUsers=");
        builder.append(methodUsers);
        builder.append(", markup=");
        builder.append(markup);
        builder.append(", spell=");
        builder.append(spell);
        builder.append(", fiveStroke=");
        builder.append(fiveStroke);
        builder.append(", invoiceName=");
        builder.append(invoiceName);
        builder.append(", antibiotics=");
        builder.append(antibiotics);
        builder.append(", dose=");
        builder.append(dose);
        builder.append(", numberDays=");
        builder.append(numberDays);
        builder.append(", dtId=");
        builder.append(dtId);
        builder.append(", dState=");
        builder.append(dState);
        builder.append(", dcost=");
        builder.append(dcost);
        builder.append(", dsale=");
        builder.append(dsale);
        builder.append(", insurValue=");
        builder.append(insurValue);
        builder.append("]");
        return builder.toString();
    }
	
}
