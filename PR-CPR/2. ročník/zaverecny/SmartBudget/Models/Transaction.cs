namespace SmartBudget.Models
{
    public enum TransactionType
    {
        Income,   // příjem
        Expense   // výdaj
    }

    public class Transaction
    {
        public int Id { get; set; }
        public decimal Amount { get; set; }
        public DateTime Date { get; set; }
        public TransactionType Type { get; set; }
        public string Note { get; set; } = "";

        // Cizí klíče
        public int UserId { get; set; }
        public User? User { get; set; }

        public int? CategoryId { get; set; }       // null = bez kategorie
        public Category? Category { get; set; }
    }
}