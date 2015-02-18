# kalender
Fellesprosjekt i PU og DB 2015

[MySQL ved NTNU](https://innsida.ntnu.no/wiki/-/wiki/Norsk/Bruke+MySQL+ved+NTNU) | 
[JDBC tutorial](http://docs.oracle.com/javase/tutorial/jdbc/overview/)

[Mulig å koble til databasen utenfor NTNU med VPN?](https://innsida.ntnu.no/wiki/-/wiki/Norsk/installere+VPN)

[Opprett GitHub-konto](https://github.com/join)

## Innføring i Git
Mer detaljert: [Git - The Simple Guide](http://rogerdudler.github.io/git-guide/) | 
[GitHub Help - Set Up Git](https://help.github.com/articles/set-up-git/) | 
[Atlassian Git Tutorial](https://www.atlassian.com/git/tutorials/comparing-workflows/centralized-workflow)

### Oppsett
- Installer Git: [Windows](http://msysgit.github.io/) | [Mac](https://code.google.com/p/git-osx-installer/downloads/list?can=3)
- Start Git Bash
- Initialiser Git og klon remote repository:
```
mkdir git                                           # Lag ny mappe
cd git                                              # Åpne mappe
git init                                            # Lag lokalt repository
git clone https://github.com/thomahan/kalender.git  # Kopier remote repository til lokal arbeidsmappe*
cd kalender                                         # Åpne lokal arbeidsmappe
```
*Trykk `Insert` i Git Bash for å lime inn.
- Før man kan pushe endringer til remote repository, må man koble lokalt repository til GitHub-bruker:
```
git config --global user.email "email"
git config --global user.name "username"
```
- For å slippe å logge inn ved hver push kan man enten la Git cache innloggingsinfo midlertidig eller lagre det permanent:
```
git config --global credential.helper cache                   # Caches i 15 minutter
git config --global credential.helper 'cache --timeout=3600'  # Caches i 1 time
git config --global credential.helper store                   # Lagres permanent
```

### Arbeidsflyt
```
git pull                          # Oppdaterer lokalt repository
<gjør endringer>
git status                        # Viser uoverensstemmelser mellom arbeidsmappe, indeks-fil og HEAD
                                  # (Kan gjøres mellom hvert steg for å ha fullstendig oversikt)
git add <filename>                # Legger endringer til indeks (stageing)
git commit -m "<commit message>"  # Committer endringer til HEAD
git push                          # Sender endringer til remote repository
```
- Ved push error, gjenta arbeidsflyten for å fikse filen med merge conflict:
```
git pull
<få merge conflict>
<fiks filen>
git add <filename>
git commit -m "<commit message>"
git push
```
