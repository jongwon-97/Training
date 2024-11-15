from gtts import gTTS
from playsound import playsound
import os

os.chdir(os.path.dirname(os.path.abspath(__file__)))
text="형배님 언능 돌아와아아아아 "

tts=gTTS(text=text ,lang="ko")
tts.save("hi.mp3")
playsound("hi.mp3")
