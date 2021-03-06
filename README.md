# bulb
## Bristol University Laptop Band
### Directed by Dr. Kelcey Swain

*It turns out that you need to email me your github username first. I tried to make it open access but it didn't work.*

To register your interest please append your name and email address to the following table:

| Name                 | a.k.a.                                                   | github username     |
| ---                  | ---                                                             | ---                 |
| Kelcey Swain         | MC Quelle Surprise                                              | kelceyswain         |
| Harris Ferguson      | Emb-Harris-ment                                                 | harrisferguson      |
| Mital Dodhia         | Mital D'oh Dear                                                 | immaculatepowerlord |
| Dom McDonald         | Dom McDonald had a Farm                                         | DomMcDonald         |
| Ping Bai             | Machu Pingu                                                     | machupingu          |
| Aaron Grice          | Aaron of Grice and Men                                          | GitGrice            |
| Shaun Wood           | Shaun 'Really Shouldn't, But he' Wood                           | shaunmight          |
| Ellis Domenger-Boyce | Ellis Domenger-Boyce                                            | ed14537             |

In order to do this you must *clone* the repository at [https://github.com/kelceyswain/bulb.git](https://github.com/kelceyswain/bulb.git). This will download a copy of everything on to your computer. Here you can edit anything and re-upload it back to the repository.

Install git on your computer. It can be found [here](https://git-scm.com/downloads).

The first step downloads the repository:
```bash
git clone https://github.com/kelceyswain/bulb.git
```

Then enter the directory you have just downloaded:
```bash
cd bulb
```

Use a text editor (here I am just using the word edit, but you can use anything) to change this file:
```bash
edit README.md
```

Next you must commit the changes you have just made, this step also requires you to give a little description of what you have edited, here I am just going to say "added my name".
```bash
git commit README.md -m "added my name"
```

The final step requires you to have a [github](https://github.com/) github account and it will ask for your login details.
```bash
git push
```

Once you have done this you will find that you also have in your `bulb` folder all the SuperCollider files that you need to get started. Don't forget, this folder can change without you knowing about it, so to update your local folder you need to go into it and type:

```bash 
git pull
```

Once you have done all of this you must download and install SuperCollider from [here](http://supercollider.github.io/download)
