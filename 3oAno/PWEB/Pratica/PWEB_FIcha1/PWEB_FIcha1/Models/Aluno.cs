using System;
using System.ComponentModel.DataAnnotations;

namespace PWEB_FIcha1.Models
{
	public class Aluno
	{
        //[Key] -> se nao meter Id e meter por exemplo alunoId tenho que meter [Key] em cima
		public int Id { get; set; }
        public string Name { get; set; }
        public string Mail { get; set; }
    }
}

