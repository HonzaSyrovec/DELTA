using SmartBudget.Data;
using SmartBudget.Helpers;
using SmartBudget.Models;
using SmartBudget.Services;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows.Input;
using Microsoft.EntityFrameworkCore;

namespace SmartBudget.ViewModels
{
    public class MainViewModel : BaseViewModel
    {
        private readonly TransactionService _transactionService;
        private readonly User _currentUser;

        private decimal _balance;
        private decimal _monthlyExpenses;
        private string _newNote = "";
        private string _newAmount = "";
        private bool _isIncome = true;
        private string _errorMessage = "";
        private string _filterType = "Vše";

        public decimal Balance
        {
            get => _balance;
            set => SetProperty(ref _balance, value);
        }

        public decimal MonthlyExpenses
        {
            get => _monthlyExpenses;
            set => SetProperty(ref _monthlyExpenses, value);
        }

        public string NewNote
        {
            get => _newNote;
            set => SetProperty(ref _newNote, value);
        }

        public string NewAmount
        {
            get => _newAmount;
            set => SetProperty(ref _newAmount, value);
        }

        public bool IsIncome
        {
            get => _isIncome;
            set => SetProperty(ref _isIncome, value);
        }

        public string ErrorMessage
        {
            get => _errorMessage;
            set => SetProperty(ref _errorMessage, value);
        }

        public string FilterType
        {
            get => _filterType;
            set
            {
                SetProperty(ref _filterType, value);
                ApplyFilter();
            }
        }
        private string _searchText = "";
        public string SearchText
        {
            get => _searchText;
            set
            {
                SetProperty(ref _searchText, value);
                ApplyFilter();
            }
        }

        public string CurrentUsername => $"Přihlášen: {_currentUser.Username}";

        public ObservableCollection<Transaction> Transactions { get; set; } = new();
        public ObservableCollection<Transaction> FilteredTransactions { get; set; } = new();

        public ICommand AddTransactionCommand { get; }
        public ICommand DeleteTransactionCommand { get; }
        public ICommand LogoutCommand { get; }
        public ICommand FilterAllCommand { get; }
        public ICommand FilterIncomeCommand { get; }
        public ICommand FilterExpenseCommand { get; }

        public Action? OnLogout { get; set; }

        private readonly CategoryService _categoryService;

        private Category? _selectedCategory;
        private string _newCategoryName = "";

        public ObservableCollection<Category> Categories { get; set; } = new();

        public Category? SelectedCategory
        {
            get => _selectedCategory;
            set => SetProperty(ref _selectedCategory, value);
        }

        public string NewCategoryName
        {
            get => _newCategoryName;
            set => SetProperty(ref _newCategoryName, value);
        }

        public ICommand AddCategoryCommand { get; }
        public ICommand DeleteCategoryCommand { get; }
        public MainViewModel(User user)
        {
            _currentUser = user;
            _transactionService = new TransactionService(new AppDbContext());

            AddTransactionCommand = new RelayCommand(ExecuteAddTransaction);

            DeleteTransactionCommand = new RelayCommand(ExecuteDeleteTransaction);

            LogoutCommand = new RelayCommand(_ => OnLogout?.Invoke());

            FilterAllCommand = new RelayCommand(_ => FilterType = "Vše");
            FilterIncomeCommand = new RelayCommand(_ => FilterType = "Příjmy");
            FilterExpenseCommand = new RelayCommand(_ => FilterType = "Výdaje");

            _categoryService = new CategoryService(new AppDbContext());
            AddCategoryCommand = new RelayCommand(ExecuteAddCategory);
            DeleteCategoryCommand = new RelayCommand(ExecuteDeleteCategory);

            LoadData();
        }

        private void LoadData()
        {
            Transactions.Clear();
            foreach (var t in _transactionService.GetTransactions(_currentUser.Id))
                Transactions.Add(t);
            Categories.Clear();
            foreach (var c in _categoryService.GetCategories(_currentUser.Id))
                Categories.Add(c);

            Balance = _transactionService.GetBalance(_currentUser.Id);
            MonthlyExpenses = _transactionService.GetMonthlyExpenses(_currentUser.Id);
            ApplyFilter();
        }

        private void ApplyFilter()
        {
            FilteredTransactions.Clear();

            foreach (var t in Transactions)
            {
                if (FilterType == "Příjmy" && t.Type != TransactionType.Income)
                    continue;
                if (FilterType == "Výdaje" && t.Type != TransactionType.Expense)
                    continue;
                if (!string.IsNullOrWhiteSpace(SearchText) &&
                    !t.Note.Contains(SearchText, StringComparison.OrdinalIgnoreCase))
                    continue;

                FilteredTransactions.Add(t);
            }
        }

        private void ExecuteAddTransaction(object? _)
        {
            if (!decimal.TryParse(NewAmount.Replace(",", "."),
                System.Globalization.NumberStyles.Any,
                System.Globalization.CultureInfo.InvariantCulture,
                out decimal amount) || amount <= 0)
            {
                ErrorMessage = "Zadej platnou částku.";
                return;
            }

            var type = IsIncome ? TransactionType.Income : TransactionType.Expense;
            _transactionService.AddTransaction(
            _currentUser.Id, amount, type, NewNote, SelectedCategory?.Id);

            NewAmount = "";
            NewNote = "";
            ErrorMessage = "";

            LoadData();
        }

        private void ExecuteDeleteTransaction(object? parameter)
        {
            if (parameter is Transaction t)
            {
                _transactionService.DeleteTransaction(t.Id);
                LoadData();
            }
        }
        private void ExecuteAddCategory(object? _)
        {
            if (string.IsNullOrWhiteSpace(NewCategoryName)) return;

            bool success = _categoryService.AddCategory(_currentUser.Id, NewCategoryName);
            if (success)
            {
                NewCategoryName = "";
                LoadData();
            }
            else
            {
                ErrorMessage = "Kategorie s tímto názvem již existuje.";
            }
        }

        private void ExecuteDeleteCategory(object? parameter)
        {
            if (parameter is Category c)
            {
                _categoryService.DeleteCategory(c.Id);
                LoadData();
            }
        }
    }
}