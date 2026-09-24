using SmartBudget.Data;
using SmartBudget.Helpers;
using SmartBudget.Services;
using System.Windows.Input;

namespace SmartBudget.ViewModels
{
    public class RegisterViewModel : BaseViewModel
    {
        private readonly AuthService _authService;

        private string _username = "";
        private string _errorMessage = "";
        private string _successMessage = "";

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

        public string SuccessMessage
        {
            get => _successMessage;
            set => SetProperty(ref _successMessage, value);
        }

        public Action? OnNavigateToLogin { get; set; }

        public ICommand RegisterCommand { get; }
        public ICommand NavigateToLoginCommand { get; }

        public RegisterViewModel()
        {
            _authService = new AuthService(new AppDbContext());
            RegisterCommand = new RelayCommand(ExecuteRegister, _ => !string.IsNullOrWhiteSpace(Username));
            NavigateToLoginCommand = new RelayCommand(_ => OnNavigateToLogin?.Invoke());
        }

        private void ExecuteRegister(object? parameter)
        {
            string password = parameter as string ?? "";

            if (string.IsNullOrWhiteSpace(password))
            {
                ErrorMessage = "Zadej heslo.";
                return;
            }

            if (password.Length < 4)
            {
                ErrorMessage = "Heslo musí mít alespoň 4 znaky.";
                return;
            }

            bool success = _authService.Register(Username, password);
            if (!success)
            {
                ErrorMessage = "Uživatel s tímto jménem již existuje.";
                return;
            }

            ErrorMessage = "";
            SuccessMessage = "Účet vytvořen! Přihlas se.";
            OnNavigateToLogin?.Invoke();
        }
    }
}