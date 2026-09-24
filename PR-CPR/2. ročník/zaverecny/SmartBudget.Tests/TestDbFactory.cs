using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;
using SmartBudget.Data;

namespace SmartBudget.Tests
{
    public static class TestDbFactory
    {
        public static AppDbContext CreateInMemoryContext()
        {
            // SQLite in-memory databáze - žije po dobu otevøeného connectionu
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();

            var options = new DbContextOptionsBuilder<AppDbContext>()
                .UseSqlite(connection)
                .Options;

            var context = new AppDbContext(options);
            context.Database.EnsureCreated();
            return context;
        }
    }
}