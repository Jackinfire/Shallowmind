public class Patient {
    // Attributes
    private int patientId;
    private String name;
    private String imagePath;
    private int age;
    private String gender;
    private String diagnosis;
    private String diagnosisDate;
    private String posture;
    private String roomNumber;
    private String contactNumber;
    private String docInCharge;
    private String medication;
    private String schedule;
    private String interventionTime;
    private String interventionHandledBy;

    public Patient(int patientId, String name, String imagePath, int age, String gender, String diagnosis,String diagnosisDate, String posture, String roomNumber, String contactNumber,String docincharge,String medication,String schedule,String interventionHandledBy,String interventionTime, boolean isCritical) {
        this.patientId = patientId;
        this.name = name;
        this.imagePath = imagePath;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.posture = posture;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.docInCharge= docincharge;
        this.diagnosisDate=diagnosis_date;
        this.medication=medication;
        this.schedule= schedule;
        this.interventionTime=interventionTime;
        this.interventionHandledBy=interventionHandledBy;
    }

    // Getters and Setters

    /*** Get methods ***/:
    public int getPatientId() {
        return patientId;
    }
    public String getInterventiontime(){
        return interventionTime;
    }
    public String getInterventionHandledBy(){
        return interventionHandledBy;
    }

    public String getSchedule(){
        return schedule;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPosture() {
        return posture;
    }

    public String getMedication(){
        return medication;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDocincharge(){
        return docInCharge;
    }
    public String  getDiagnosisdate(){
        return diagnosisDate;
    }

    public String getPatientDetails() {
        return  "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Diagnosis: " + diagnosis + "\n" +
                "Diagnosis Date:"+diagnosis_date+
                "Posture: " + posture + "\n" +
                "Emergency Contact Number: " + contactNumber + "\n"+
                "Doctor in charge: "+ docInCharge;
    }

    /*** Set methods ***/:

    public void setName(String name) {
        this.name = name;
    }
    public void setImagePath() {
        this.imagePath = imagePath;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    /*** Other methods ***/
    public void updatePosture(String newPosture) {
        this.posture = newPosture;
    }

    public void printPatientDetails() {
        System.out.println(getPatientDetails());
    }
}
