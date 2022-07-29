public class Parents {
    float dadKinship;
    float momKinship;


    public Parents(float dadKinship, float momKinship) {
        this.dadKinship = dadKinship;
        this.momKinship = momKinship;
    }

    public Parents() {
        this.dadKinship = -1;
        this.momKinship = -1;
    }

    public float getDadKinship() {
        return this.dadKinship;
    }

    public void setDadKinship(float dadKinship) {
        this.dadKinship = dadKinship;
    }

    public float getMomKinship() {
        return this.momKinship;
    }

    public void setMomKinship(float momKinship) {
        this.momKinship = momKinship;
    }

}