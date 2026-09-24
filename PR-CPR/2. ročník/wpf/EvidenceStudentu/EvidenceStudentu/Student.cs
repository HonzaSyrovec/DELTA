using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace EvidenceStudentu
{
    public class Student
    {
       static int pocet = 1;
        int id;
        string jmeno;
        string prijmeni;
        int vek;

        public int Vek { get => vek; set => vek = value; }
        public string Prijmeni { get => prijmeni; set => prijmeni = value; }
        public string Jmeno { get => jmeno; set => jmeno = value; }
        public int Id { get => id; set => id = value; }

      
        public Student(string jmeno, string prijmeni, int vek)
        {
            this.id= pocet;
            Jmeno = jmeno;
            Prijmeni = prijmeni;
            Vek = vek;
            pocet++;
        }
       

        public override string ToString()
        {
            return "Student id:" + this.id + "jmeno: " + this.jmeno + " prijmeni: " + this.prijmeni + " vek: " + this.vek;
        }
    }
}
