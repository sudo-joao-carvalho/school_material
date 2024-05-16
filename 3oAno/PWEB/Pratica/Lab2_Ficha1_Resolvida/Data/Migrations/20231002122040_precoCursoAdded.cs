using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Ficha1_P1_V1.Data.Migrations
{
    public partial class precoCursoAdded : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<decimal>(
                name: "Preco",
                table: "Cursos",
                type: "decimal(18,2)",
                nullable: true);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Preco",
                table: "Cursos");
        }
    }
}
