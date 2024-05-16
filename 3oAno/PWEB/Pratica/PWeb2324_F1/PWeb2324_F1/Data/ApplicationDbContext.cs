using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

using PWeb2324_F1.Models;

namespace PWeb2324_F1.Data;

public class ApplicationDbContext : IdentityDbContext
{

    public DbSet<Aluno> Alunos { get; set; }

    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
        : base(options)
    {
    }
}

