public class Patient {
    // Attributes
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String diagnosis;
    private String diagnosis_date;
    private String posture;
    private String roomNumber;
    private String contactNumber;
    private String Docincharge;


    public Patient(int patientId, String name, int age, String gender, String diagnosis,String diagnosis_date, String posture, String roomNumber, String contactNumber,String docincharge, boolean isCritical) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.posture = posture;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.Docincharge= docincharge;
        this.diagnosis_date=diagnosis_date;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public void updatePosture(String newPosture) {
        this.posture = newPosture;
    }
    public String getDocincharge(){
        return Docincharge;
    }
    public String  getDiagnosis_date(){
        return diagnosis_date;
    }

    public String getPatientDetails() {
        return  "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Diagnosis: " + diagnosis + "\n" +
                "Diagnosis Date:"+diagnosis_date+
                "Posture: " + posture + "\n" +
                "Emergency Contact Number: " + contactNumber + "\n"+
                "Doctor in charge: "+ Docincharge;
    }

    public void printPatientDetails() {
        System.out.println(getPatientDetails());
    }
}
