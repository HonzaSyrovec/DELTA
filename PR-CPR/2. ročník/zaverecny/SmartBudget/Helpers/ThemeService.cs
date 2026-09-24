using System;
using System.Windows;

namespace SmartBudget.Helpers
{
    public static class ThemeService
    {
        private static bool _isDark = false;

        public static bool IsDark => _isDark;

        public static void Toggle()
        {
            _isDark = !_isDark;
            Apply();
        }

        public static void Apply()
        {
            var dict = new ResourceDictionary
            {
                Source = new Uri(_isDark
                    ? "Themes/DarkTheme.xaml"
                    : "Themes/LightTheme.xaml",
                    UriKind.Relative)
            };

            var merged = Application.Current.Resources.MergedDictionaries;
            merged.Clear();
            merged.Add(dict);
        }
    }
}