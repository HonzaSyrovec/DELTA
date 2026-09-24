namespace SmartBudget.Models
{
    public class Category
    {
        public int Id { get; set; }
        public string Name { get; set; } = "";
        public decimal? MonthlyLimit { get; set; }  // null = bez limitu (F008)

        // Cizí klíč
        public int UserId { get; set; }
        public User? User { get; set; }

        public List<Transaction> Transactions { get; set; } = new();
    }
}