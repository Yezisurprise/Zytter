# Zytter

《Zytter》是一款基于Java三层C/S结构的一对一回合制对战小游戏。

两名玩家分别从英雄池中选取3名英雄进行回合制对战。

- 初始回合为35（可在对局中的商店购买“回合延时道具”来扩展5个回合）
- 玩家行动优先权基于“行动力算法”决定，具体内容在游戏性中注明
- 胜利条件：击杀对方所有英雄
- 当回合耗尽且未分出胜负时，按照如下规则判定：
1）英雄数量多或者英雄当前生命值百分比高的玩家获胜；
2）当生命值百分比相同时，具体生命值高的玩家获胜；
3）当具体生命值相同时，匹配房间拥有者获胜。

一下是英语机翻（主要是懒）。

Zytter is a one-to-one round game based on Java three-tier C / S structure.

Two players choose three heroes from the hero pool to fight in turn.

- The initial round is 35 (you can buy "round delay props" in the shop in the match to expand 5 rounds)
- The player's action priority is determined based on the "action power algorithm", and the specific content is indicated in the gameplay
- Victory condition: kill all heroes of the opponent
- When the round is exhausted and there is no winner, the following rules shall be followed:
1) Players with a large number of heroes or a high percentage of their current HP win;
2) When the percentage of HP is the same, the player with high HP wins;
3) When the specific health value is the same, the match room owner wins.

详细中文文档：https://www.wrss.org/p/28
更新日志：https://www.wrss.org/zytter.html
