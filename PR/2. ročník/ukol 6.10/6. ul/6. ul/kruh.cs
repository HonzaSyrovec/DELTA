using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _6._ul
{
    internal class kruh
    {
        public double polomer;

        public double vypocitej_obvod()
        {
            return 2 * Math.PI * polomer;
        }

        public double vypocitej_obsah()
        {
            return Math.PI * polomer * polomer;
        }
    }
}
