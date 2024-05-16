using System.ComponentModel.DataAnnotations;

namespace Ficha1_P1_V1.Models
{
    public class Curso
    {
        public int Id { get; set; }

        //Annotations
        [Display(Name = "Nome", Prompt = "Introduza a Nome do curso!")]
        public string Nome { get; set; }
        [Display(Name = "Disponivel", Prompt = "Disponivel?")]
        public bool Disponivel { get; set; }
        [Display(Name = "Categoria", Prompt = "Introduza a Categoria do curso!")]
        public string Categoria { get; set; }
        [Display(Name = "Descrição", Prompt = "Introduza a Descrição do curso!")]
        public string Descricao { get; set; }
        [Display(Name = "Descrição Resumida", Prompt = "Introduza a Nome do curso!")]
        public string DescricaoResumida { get; set; }
        [Display(Name = "Requisitos", Prompt = "Introduza os Requisitos do curso!")]
        public string Requisitos { get; set; }
        [Display(Name = "Idade Minima", Prompt = "Introduza a Idade Minima do curso!", Description ="Idade minima para poder frequentar esta formação")]
        [Range(14, 100, ErrorMessage = "Idade Minima: 14 anos, máximo: 100 anos" )]
        public int IdadeMinima { get; set; }

        [Display(Name ="Preço")]
        public Decimal? Preco { get; set; } //com ? é nullable, ou seja pode ser null

    }

}
