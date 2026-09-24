using SmartBudget.Models;
using SmartBudget.Services;
using System.Linq;

namespace SmartBudget.Tests
{
    public class TransactionServiceTests
    {
        [Fact] // F003, F004, F005
        public void AddTransaction_IncomeAndExpense_BalanceIsCorrect()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);
            auth.Register("petr", "heslo123");
            var user = auth.Login("petr", "heslo123")!;

            var service = new TransactionService(db);
            service.AddTransaction(user.Id, 5000, TransactionType.Income, "Výplata");
            service.AddTransaction(user.Id, 200, TransactionType.Expense, "Oběd");

            decimal balance = service.GetBalance(user.Id);

            Assert.Equal(4800, balance);
        }

        [Fact] // F006
        public void DeleteTransaction_RemovesTransactionAndUpdatesBalance()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);
            auth.Register("petr", "heslo123");
            var user = auth.Login("petr", "heslo123")!;

            var service = new TransactionService(db);
            service.AddTransaction(user.Id, 5000, TransactionType.Income, "Výplata");
            service.AddTransaction(user.Id, 200, TransactionType.Expense, "Oběd");

            var toDelete = service.GetTransactions(user.Id).First(t => t.Note == "Oběd");
            service.DeleteTransaction(toDelete.Id);

            Assert.Equal(5000, service.GetBalance(user.Id));
            Assert.Single(service.GetTransactions(user.Id));
        }

        [Fact] // F014
        public void GetMonthlyExpenses_OnlyCountsCurrentMonthExpenses()
        {
            var db = TestDbFactory.CreateInMemoryContext();
            var auth = new AuthService(db);
            auth.Register("petr", "heslo123");
            var user = auth.Login("petr", "heslo123")!;

            var service = new TransactionService(db);
            service.AddTransaction(user.Id, 5000, TransactionType.Income, "Výplata");
            service.AddTransaction(user.Id, 200, TransactionType.Expense, "Oběd");

            decimal monthlyExpenses = service.GetMonthlyExpenses(user.Id);

            Assert.Equal(200, monthlyExpenses);
        }
    }
}