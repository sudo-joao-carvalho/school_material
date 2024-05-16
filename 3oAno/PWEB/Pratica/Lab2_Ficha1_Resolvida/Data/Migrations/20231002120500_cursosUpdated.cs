using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Ficha1_P1_V1.Data.Migrations
{
    public partial class cursosUpdated : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Grau",
                table: "Cursos",
                newName: "Requisitos");

            migrationBuilder.RenameColumn(
                name: "Designacao",
                table: "Cursos",
                newName: "Nome");

            migrationBuilder.AddColumn<string>(
                name: "Categoria",
                table: "Cursos",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<string>(
                name: "Descricao",
                table: "Cursos",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<string>(
                name: "DescricaoResumida",
                table: "Cursos",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<bool>(
                name: "Disponivel",
                table: "Cursos",
                type: "bit",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<int>(
                name: "IdadeMinima",
                table: "Cursos",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Categoria",
                table: "Cursos");

            migrationBuilder.DropColumn(
                name: "Descricao",
                table: "Cursos");

            migrationBuilder.DropColumn(
                name: "DescricaoResumida",
                table: "Cursos");

            migrationBuilder.DropColumn(
                name: "Disponivel",
                table: "Cursos");

            migrationBuilder.DropColumn(
                name: "IdadeMinima",
                table: "Cursos");

            migrationBuilder.RenameColumn(
                name: "Requisitos",
                table: "Cursos",
                newName: "Grau");

            migrationBuilder.RenameColumn(
                name: "Nome",
                table: "Cursos",
                newName: "Designacao");
        }
    }
}
