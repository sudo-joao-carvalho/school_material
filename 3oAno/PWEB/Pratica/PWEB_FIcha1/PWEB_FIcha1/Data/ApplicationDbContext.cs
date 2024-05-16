using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using PWEB_FIcha1.Models;

namespace PWEB_FIcha1.Data;

public class ApplicationDbContext : IdentityDbContext
{

    public DbSet<Aluno> Alunos { get; set; }

    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
        : base(options)
    {
    }
}

