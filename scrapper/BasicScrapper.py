import requests
import time
url = "https://www.flipkart.com/search?q=titan+watch&sid=r18%2Cf13&as=on&as-show=on&otracker=AS_QueryStore_OrganicAutoSuggest_2_6_na_na_na&otracker1=AS_QueryStore_OrganicAutoSuggest_2_6_na_na_na&as-pos=2&as-type=RECENT&suggestionId=titan+watch%7CWrist+Watches&requestId=592847c5-6ded-44a7-b949-811cb6ceb752&as-searchtext=titan%20"


session  = requests.Session()
header = {
    "Accept-Language": "en-US,en;q=0.9",
    "Accept-Encoding":'gzip,deflate,br',
    'Connection': 'keep-alive',
    'Referer': 'https://www.google.com'
}
time.sleep(2)

req = session.get(url,headers=header)

with open("file.html","w") as f:
    f.write(req.text)




