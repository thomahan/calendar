# kalender
Fellesprosjekt i PU og DB 2015

[MySQL ved NTNU](https://innsida.ntnu.no/wiki/-/wiki/Norsk/Bruke+MySQL+ved+NTNU)

[Mulig å koble til databasen utenfor NTNU med VPN?](https://innsida.ntnu.no/wiki/-/wiki/Norsk/installere+VPN)

## Oppsett av Git

[Git - The Simple Guide](http://rogerdudler.github.io/git-guide/)

## Kortversjon:
### Oppsett
- Installer Git: [Windows](http://msysgit.github.io/) | [Mac](https://code.google.com/p/git-osx-installer/downloads/list?can=3)
- Start Git Bash
```
mkdir git         # Lag ny mappe
cd git            # Åpne mappe
git init          # Lag lokalt repository
git clone <url>   # Kopier online repository til lokal arbeidsmappe
```
Trykk `Insert` i Git Bash for � lime inn.
Assossiere commits med GitHub-bruker
```
git config --global user.name "username"
git config --global user.email "email_on_github"
```
Cache passord slik at man slipper å logge inn ved hver push.
```
git config --global credential.helper cache                   # Standard timeout er 15 minutter
git config --global credential.helper 'cache --timeout=3600'  # Setter timeout til én time
```

Arbeidsflyt
```
git pull                                                    # Oppdater lokal mappe
<gjør endringer>
git status                                                  # Se hvordan lokal mappe skiller seg fra online repository
git add <filename>                                          # Legg filer/endringer til lokal mappe
git commit -m "<commit message (what did you do and why)>"  # Legge filer/endringer til branch
git push                                                    # Pusher alle commits til repository
```
