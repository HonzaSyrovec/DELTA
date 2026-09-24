using SmartBudget.Data;
using SmartBudget.Helpers;
using SmartBudget.Models;
using SmartBudget.Services;
using System.Windows.Input;

namespace SmartBudget.ViewModels
{
    public class LoginViewModel : BaseViewModel
    {
        private readonly AuthService _authService;

        private string _username = "";
        private string _errorMessage = "";

        public string Username
        {
            get => _username;
            set => SetProperty(ref _username, value);
        }

        public string ErrorMessage
        {
            get => _errorMessage;
            set => SetProperty(ref _errorMessage, value);
        }

        // Akce které se spustí po úspěšném přihlášení/registraci — nastaví MainWindow
        public Action<User>? OnLoginSuccess { get; set; }
        public Action? OnNavigateToRegister { get; set; }

        public ICommand LoginCommand { get; }
        public ICommand NavigateToRegisterCommand { get; }

        public LoginViewModel()
        {
            _authService = new AuthService(new AppDbContext());
            LoginCommand = new RelayCommand(ExecuteLogin, _ => !string.IsNullOrWhiteSpace(Username));
            NavigateToRegisterCommand = new RelayCommand(_ => OnNavigateToRegister?.Invoke());
        }

        private void ExecuteLogin(object? parameter)
        {
            // heslo přichází jako parameter z code-behind
            string password = parameter as string ?? "";

            if (string.IsNullOrWhiteSpace(password))
            {
                ErrorMessage = "Zadej heslo.";
                return;
            }

            var user = _authService.Login(Username, password);
            if (user == null)
            {
                ErrorMessage = "Špatné jméno nebo heslo.";
                return;
            }

            ErrorMessage = "";
            OnLoginSuccess?.Invoke(user);
        }
    }
}