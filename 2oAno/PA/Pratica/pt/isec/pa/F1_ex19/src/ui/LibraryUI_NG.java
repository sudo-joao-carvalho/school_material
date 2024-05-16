package ui;

public class LibraryUI_NG {

    /*void addBook() {
        int type = PAInput.chooseOption("Book type","Old Book","Recent book","Cancel operation");
        if (type == 3 || type < 1)
            return;
        String title = PAInput.readString("Book title: ",false);
        String author;
        ArrayList<String> authors = new ArrayList<>();
        do {
            author = PAInput.readString("Name of one author [\'exit\' to finish]: ",false);
            if (author!=null && !author.equalsIgnoreCase("exit"))
                authors.add(author);
        } while (!author.equalsIgnoreCase("exit"));
        if (authors.isEmpty())
            authors.add("Author unknown");
        //int id = lib.addBook(title,authors);
        int id = switch (type) {
            case 1 -> {
                int nr_copies = PAInput.readInt("Number of copies: ");
                yield lib.addBook(new OldBook(title,authors,nr_copies));
            }
            case 2 -> {
                String isbn = PAInput.readString("ISBN: ",false);
                double cost = PAInput.readNumber("Cost: ");
                yield lib.addBook(new RecentBook(title,authors,isbn,cost));
            }
            default -> -1;
        };
        if (id<0)
            System.out.println("Error adding this new book");
        else
            System.out.printf("The ID of this new book is: %d\n",id);
    }*/
}
