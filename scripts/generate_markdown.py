import os
import re

def extraire_javadoc(chemin):
    fichiers_markdown = []
    for root, _, files in os.walk(chemin):
        for file in files:
            if file.endswith(".java"):
                chemin_fichier = os.path.join(root, file)
                with open(chemin_fichier, "r") as f:
                    contenu = f.read()
                # Regex pour les JavaDocs
                regex_javadoc = re.compile(r"/\*\*(.*?)\*/\s*(public|private|protected)\s+[\w<>]+\s+(\w+)\(", re.DOTALL)
                markdown = f"# Documentation de `{file}`\n\n"
                for match in regex_javadoc.finditer(contenu):
                    commentaire = match.group(1).strip()
                    nom_methode = match.group(3)
                    markdown += f"## Méthode `{nom_methode}`\n\n{commentaire.replace('* ', '').strip()}\n\n"
                # Sauvegarde en fichier Markdown
                fichier_markdown = os.path.join("docs", file.replace(".java", ".md"))
                os.makedirs("docs", exist_ok=True)
                with open(fichier_markdown, "w") as f:
                    f.write(markdown)
                fichiers_markdown.append(fichier_markdown)
    return fichiers_markdown

# Générer les fichiers Markdown
extraire_javadoc("src/")
