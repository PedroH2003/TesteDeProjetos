import pygame
import os
import random

TELA_LARGURA = 1000
TELA_ALTURA = 700


IMAGEM_FUNDO = pygame.transform.scale2x(pygame.image.load(os.path.join('imgs', 'bg.png')))
IMAGEM_CHAO = pygame.transform.scale2x(pygame.image.load(os.path.join('imgs', 'base.png')))
IMAGEM_PASSARO = pygame.transform.scale2x(pygame.image.load(os.path.join('imgs', 'bird1.png')))

class Passaro:
    IMAGEM = IMAGEM_PASSARO
    MOVIMENTO = 10

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.tempo = 0
        self.pulo = 0

    def pular(self):
        self.pulo = -25
        self.tempo = 0

    def cair(self):
        self.tempo += 1
        deslocamento = self.pulo + (self.tempo**2) 

        if deslocamento > 0:
            deslocamento = 20

        self.y += deslocamento         

    def andar_frente(self):
        self.IMAGEM = IMAGEM_PASSARO
        self.x += self.MOVIMENTO

    def andar_tras(self):
        self.IMAGEM = pygame.transform.flip(IMAGEM_PASSARO, True, False)
        self.x -= self.MOVIMENTO

    def desenhar(self, tela):
        tela.blit(self.IMAGEM, (self.x, self.y))

    def get_mask(self):
        return pygame.mask.from_surface(self.IMAGEM)

class Chao:
    IMAGEM = IMAGEM_CHAO
    LARGURA = IMAGEM.get_width()

    def __init__(self, y):
        self.y = y
        self.x1 = 0
        self.x2 = self.LARGURA

    def mover(self):
        pass

    def desenhar(self, tela):
        tela.blit(self.IMAGEM, (self.x1, self.y))
        tela.blit(self.IMAGEM, (self.x2, self.y))


def desenhar(tela, chao, passaro):
    tela.blit(IMAGEM_FUNDO, (0, 0))
    chao.desenhar(tela)
    passaro.desenhar(tela)

    pygame.display.update()

def main():
    tela = pygame.display.set_mode((TELA_LARGURA, TELA_ALTURA))
    chao = Chao(650)
    passaro = Passaro(50, 650 - IMAGEM_PASSARO.get_height())

    relogio = pygame.time.Clock()
    rodando = True
    while rodando:
        relogio.tick(30) # 30 fps / 30 frames por segundo / 30 whiles por segundo 

        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                rodando = False
                pygame.QUIT
                quit()

            if evento.type == pygame.KEYDOWN:
                if evento.key == pygame.K_ESCAPE:
                    rodando = False
                    pygame.QUIT
                    quit()

                # if evento.key == pygame.K_a:
                #     passaro.andar_tras()
                # if evento.key == pygame.K_d:
                #     passaro.andar_frente()
                if evento.key == pygame.K_SPACE:
                    passaro.pular()

        if pygame.key.get_pressed()[pygame.K_a]:
            passaro.andar_tras()
        
        if pygame.key.get_pressed()[pygame.K_d]:
            passaro.andar_frente()


        passaro.cair()
        
        if passaro.y > 650 - IMAGEM_PASSARO.get_height():
            passaro.y = 650 - IMAGEM_PASSARO.get_height()
        
        desenhar(tela, chao, passaro)

if __name__ == '__main__':
    main()
