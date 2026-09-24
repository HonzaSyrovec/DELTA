using Microsoft.EntityFrameworkCore;
using SmartBudget.Data;
using SmartBudget.Models;

namespace SmartBudget.Services
{
    public class TransactionService
    {
        private readonly AppDbContext _db;

        public TransactionService(AppDbContext db)
        {
            _db = db;
        }

        // Přidání transakce (F003, F004)
        public void AddTransaction(int userId, decimal amount, TransactionType type, string note, int? categoryId = null)
        {
            var transaction = new Transaction
            {
                UserId = userId,
                Amount = amount,
                Type = type,
                Note = note,
                Date = DateTime.Now,
                CategoryId = categoryId
            };

            _db.Transactions.Add(transaction);
            _db.SaveChanges();
        }

        // Načtení všech transakcí uživatele (F010)
        public List<Transaction> GetTransactions(int userId)
        {
            return _db.Transactions
                .Include(t => t.Category)
                .Where(t => t.UserId == userId)
                .OrderByDescending(t => t.Date)
                .ToList();
        }

        // Smazání transakce (F006)
        public void DeleteTransaction(int transactionId)
        {
            var transaction = _db.Transactions.Find(transactionId);
            if (transaction != null)
            {
                _db.Transactions.Remove(transaction);
                _db.SaveChanges();
            }
        }

        // Aktuální zůstatek (F005)
        public decimal GetBalance(int userId)
        {
            var transactions = _db.Transactions
                .Where(t => t.UserId == userId)
                .ToList(); // nejdřív načti do paměti

            decimal income = transactions.Where(t => t.Type == TransactionType.Income).Sum(t => t.Amount);
            decimal expense = transactions.Where(t => t.Type == TransactionType.Expense).Sum(t => t.Amount);
            return income - expense;
        }

        // Suma výdajů za aktuální měsíc (F014)
        public decimal GetMonthlyExpenses(int userId)
        {
            var now = DateTime.Now;
            return _db.Transactions
                .Where(t => t.UserId == userId
                    && t.Type == TransactionType.Expense
                    && t.Date.Month == now.Month
                    && t.Date.Year == now.Year)
                .ToList() // nejdřív načti do paměti
                .Sum(t => t.Amount);
        }
    }
}