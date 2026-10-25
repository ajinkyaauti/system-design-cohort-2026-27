class Payment {
    private String paymentId;
    private long amount;
    private String status;

    //constructor for default payment and payment with ID and amount

    Payment(){
        this.paymentId = "";
        this.amount = 0;
        this.status = "Pending";
    }

    //constructor for payment with ID, amount
    Payment(String paymentId, long amount) {
       if(paymentId == null || paymentId.isEmpty()){
           throw new IllegalArgumentException("Payment ID cannot be null or empty");
       }
       if(amount < 0){
           throw new IllegalArgumentException("Amount cannot be negative");
       }
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = "Pending";
    }

    void status(){
        if("Pending".equals(status)){
            System.out.println("Payment is pending.");
        } else if("Completed".equals(status)){
            System.out.println("Payment is completed.");
        } else {
            System.out.println("Payment status is unknown.");
        }
    }

    void cancel(){
        if("Pending".equals(status)){
            status = "Cancelled";
            System.out.println("Payment has been cancelled.");
        } else {
            System.out.println("Payment cannot be cancelled.");
        }
    }
    
    public String getPaymentId() {
        return paymentId;
    }

    public long getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
    public static void main(String[] args) {
        Payment fistPayment = new Payment();
        // fistPayment.paymentId = "P123";
        // fistPayment.amount = 1000;
        // fistPayment.status = "Pending";
        System.out.println("Payment ID: " + fistPayment.getPaymentId());
        System.out.println("Amount: " + fistPayment.getAmount());
        System.out.println("Status: " + fistPayment.getStatus());
        fistPayment.status();

        // Payment secondPayment = new Payment();
        // secondPayment.paymentId = "P124";
        // secondPayment.amount = 2000;
        // secondPayment.status = "Completed";
        // System.out.println("Payment ID: " + secondPayment.paymentId);
        // System.out.println("Amount: " + secondPayment.amount);
        // System.out.println("Status: " + secondPayment.status);
        Payment thirdPayment = new Payment("P125", 999);
        System.out.println("Payment ID: " + thirdPayment.getPaymentId());
        System.out.println("Amount: " + thirdPayment.getAmount());
        System.out.println("Status: " + thirdPayment.getStatus());

    }
}

