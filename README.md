# kalender
Fellesprosjekt i PU og DB 2015

https://innsida.ntnu.no/wiki/-/wiki/Norsk/Bruke+MySQL+ved+NTNU

Man kan kanskje koble til databasen utenfor NTNU med VPN:
https://innsida.ntnu.no/wiki/-/wiki/Norsk/Hjemmeomr%C3%A5de+-+dine+mapper

http://help.github.com/articles/set-up-git/

http://rogerdudler.github.io/git-guide/

## Oppsett av Git

apt-get install git

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

Lag en mappe, gå inn i den og initialiser Git
```
git init
```
Klone repository til  lokal mappe
```
git clone <url>
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
