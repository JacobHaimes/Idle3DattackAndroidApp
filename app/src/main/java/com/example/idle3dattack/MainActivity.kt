package com.example.idle3dattack

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log.d
import android.view.Gravity
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt
import android.view.animation.*
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {
    var enemyHealth = 10.0
    var enemyStage = 1
    var enemyIndex = 0
    var points = 0.0
    var pointsSuffix = 0
    var playerDmg = 1.0
    var playerDmgSuffix = 0
    var mainUpgrade = 10.0
    var mainUpgradeSuffix = 0
    var squareUpgrade = 50.0
    var sqUSuffix = 0
    var pentagonUpgrade = 250.0
    var pentUSuffix = 0
    var hexagonUpgrade = 1250.0
    var hexUSuffix = 1
    var septagonUpgrade = 6250.0
    var sepUSuffix = 1
    var octagonUpgrade = 31250.0
    var octUSuffix = 1
    var squareDmg = 5.0
    var sqDSuffix = 0
    var pentagonDmg = 25.0
    var pentDSuffix = 0
    var hexagonDmg = 125.0
    var hexDSuffix = 0
    var septagonDmg = 625.0
    var sepDSuffix = 0
    var octagonDmg = 3125.0
    var octDSuffix = 0
    var enemyText = 0
    var dmgText = 1.0
    var currentEnemyMax = 10.0
    var tempEnemySave = 1.0
    val enemySize: IntArray = intArrayOf(4,6,8,10,12,16,20,24,28,32,36,40,44,48,54,60,66,72,80,90,100)
    val bigNums : CharArray = charArrayOf('\u0000','k','M','B','T','q','Q','s','S')

    var sqLvl = 0
    var peLvl = 0
    var heLvl = 0
    var seLvl = 0
    var ocLvl = 0

    val sqTimer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            enemyHealth -= squareDmg
            points += squareDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
            this.start()
        }
    }
    val peTimer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            enemyHealth -= pentagonDmg
            points += pentagonDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
            this.start()
        }
    }
    val heTimer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            enemyHealth -= hexagonDmg
            points += hexagonDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
            this.start()
        }
    }
    val seTimer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            enemyHealth -= septagonDmg
            points += septagonDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
            this.start()
        }
    }
    val ocTimer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            enemyHealth -= octagonDmg
            points += octagonDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
            this.start()
        }
    }


    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pointsText.text = "${points.roundToInt()}"
        dmgText = (playerDmg * 100.0).roundToInt() / 100.0
        currentDmg.text = "$dmgText Damage"
        this.enemyText = enemyHealth.roundToInt()
        enemyHp.text = "HP: ${enemyHealth.roundToInt()} / ${currentEnemyMax.roundToInt()}"
        currentStage.text = "Stage $enemyStage/10"
        enemyHealthBar.max = currentEnemyMax.roundToInt()
        enemyHealthBar.progress = enemyHealth.roundToInt()

        mainEnemy.setOnClickListener {
            enemyHealth -= playerDmg
            points += playerDmg
            enemyHealthBar.progress = enemyHealth.roundToInt()
            if(enemyHealth <= 0.0){
                calculateEnemyHealth()
            }
            setText()
        }

        savedInstanceState?.getDouble("enemyHealth")

        triangleColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.triange_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        squareColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.square_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        pentagonColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.pentagon_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        hexagonColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.hexagon_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        septagonColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.septagon_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        octagonColorBtn.setOnClickListener {

            d("button", "clicked")
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.octagon_colors,null)
            window.contentView = view
            val exit = view.findViewById<Button>(R.id.triangleExit)
            exit.setOnClickListener {
                window.dismiss()
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        upgradeButton.setOnClickListener {
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.upgrades_page,null)
            window.contentView = view
            view.findViewById<Button>(R.id.exitUpgrades).setOnClickListener {
                window.dismiss()
            }
            var openText = determineValue(mainUpgrade,mainUpgradeSuffix)
            view.findViewById<TextView>(R.id.playerUpgrade).text = "${if(mainUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[mainUpgradeSuffix]}"
            openText = determineValue(squareUpgrade,sqUSuffix)
            view.findViewById<TextView>(R.id.squareUpgrade).text = "${if(squareUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[sqUSuffix]}"
            openText = determineValue(pentagonUpgrade,pentUSuffix)
            view.findViewById<TextView>(R.id.pentaUpgrade).text = "${if(pentagonUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[pentUSuffix]}"
            openText = determineValue(hexagonUpgrade,hexUSuffix)
            view.findViewById<TextView>(R.id.hexUpgrade).text = "${if(hexagonUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[hexUSuffix]}"
            openText = determineValue(septagonUpgrade,sepUSuffix)
            view.findViewById<TextView>(R.id.septaUpgrade).text = "${if(septagonUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[sepUSuffix]}"
            openText = determineValue(octagonUpgrade,octUSuffix)
            view.findViewById<TextView>(R.id.octoUpgrade).text = "${if(octagonUpgrade < 1000){openText.roundToInt()} else{(openText * 100.00).roundToInt() / 100.00}}${bigNums[octUSuffix]}"
            if(mainUpgrade <= points){
                view.findViewById<Button>(R.id.playerUpgrade).isEnabled = true
            }
            if(squareUpgrade <= points){
                view.findViewById<Button>(R.id.squareUpgrade).isEnabled = true
            }
            if(pentagonUpgrade <= points){
                view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = true
            }
            if(hexagonUpgrade <= points){
                view.findViewById<Button>(R.id.hexUpgrade).isEnabled = true
            }
            if(septagonUpgrade <= points){
                view.findViewById<Button>(R.id.septaUpgrade).isEnabled = true
            }
            if(octagonUpgrade <= points){
                view.findViewById<Button>(R.id.octoUpgrade).isEnabled = true
            }
            view.findViewById<Button>(R.id.playerUpgrade).setOnClickListener {
                if(mainUpgrade <= points) {
                    points -= mainUpgrade
                    mainUpgrade *= 1.2
                    playerDmg *= 1.1

                    mainUpgradeSuffix = determineSuffix(mainUpgrade,0)
                    val tempUtext = determineValue(mainUpgrade,mainUpgradeSuffix)
                    view.findViewById<TextView>(R.id.playerUpgrade).text = "${if(mainUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[mainUpgradeSuffix]}"

                    dmgText = (playerDmg * 100.0).roundToInt() / 100.0
                    currentDmg.text = "$dmgText Damage"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"
                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }
            }
            view.findViewById<Button>(R.id.squareUpgrade).setOnClickListener {
                if(squareUpgrade <= points) {
                    points -= squareUpgrade
                    squareUpgrade *= 1.2
                    squareDmg *= 1.1
                    if(sqLvl == 0){
                        sqTimer.start()
                        squareDmg = 5.0
                    }
                    sqLvl++
                    sqUSuffix = determineSuffix(squareUpgrade,0)
                    val tempUtext = determineValue(squareUpgrade,sqUSuffix)
                    view.findViewById<TextView>(R.id.squareUpgrade).text = "${if(squareUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[sqUSuffix]}"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"
                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }

            }
            view.findViewById<Button>(R.id.pentaUpgrade).setOnClickListener {
                if(pentagonUpgrade <= points) {
                    points -= pentagonUpgrade
                    pentagonUpgrade *= 1.2
                    pentagonDmg *= 1.1
                    if(peLvl == 0){
                        peTimer.start()
                        pentagonDmg = 25.0
                    }
                    peLvl++

                    pentUSuffix = determineSuffix(pentagonUpgrade,0)
                    val tempUtext = determineValue(pentagonUpgrade,pentUSuffix)
                    view.findViewById<TextView>(R.id.pentaUpgrade).text = "${if(pentagonUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[pentUSuffix]}"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"

                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }
            }
            view.findViewById<Button>(R.id.hexUpgrade).setOnClickListener {
                if(hexagonUpgrade <= points) {
                    points -= hexagonUpgrade
                    hexagonUpgrade *= 1.2
                    hexagonDmg *= 1.1
                    if(heLvl == 0){
                        heTimer.start()
                        hexagonDmg = 125.0
                    }
                    heLvl++

                    hexUSuffix = determineSuffix(hexagonUpgrade,0)
                    val tempUtext = determineValue(hexagonUpgrade,hexUSuffix)
                    view.findViewById<TextView>(R.id.hexUpgrade).text = "${if(hexagonUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[hexUSuffix]}"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"

                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }
            }
            view.findViewById<Button>(R.id.septaUpgrade).setOnClickListener {
                if(septagonUpgrade <= points) {
                    points -= septagonUpgrade
                    septagonUpgrade *= 1.2
                    septagonDmg *= 1.1
                    if(seLvl == 0){
                        seTimer.start()
                        septagonDmg = 625.0
                    }
                    seLvl++

                    sepUSuffix = determineSuffix(septagonUpgrade,0)
                    val tempUtext = determineValue(septagonUpgrade,sepUSuffix)
                    view.findViewById<TextView>(R.id.septaUpgrade).text = "${if(septagonUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[sepUSuffix]}"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"
                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }
            }
            view.findViewById<Button>(R.id.octoUpgrade).setOnClickListener {
                if(octagonUpgrade <= points) {
                    points -= octagonUpgrade
                    octagonUpgrade *= 1.2
                    octagonDmg *= 1.1
                    if(ocLvl == 0){
                        ocTimer.start()
                        octagonDmg = 3125.0
                    }
                    ocLvl++

                    octUSuffix = determineSuffix(octagonUpgrade,0)
                    val tempUtext = determineValue(octagonUpgrade,octUSuffix)
                    view.findViewById<TextView>(R.id.octoUpgrade).text = "${if(octagonUpgrade < 1000){tempUtext.roundToInt()} else{(tempUtext * 100.00).roundToInt() / 100.00}}${bigNums[octUSuffix]}"

                    pointsSuffix = determineSuffix(points,0)
                    val tempPoints = determineValue(points,pointsSuffix)
                    pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"
                }
                if(mainUpgrade > points){ view.findViewById<Button>(R.id.playerUpgrade).isEnabled = false }
                if(squareUpgrade > points){ view.findViewById<Button>(R.id.squareUpgrade).isEnabled = false }
                if(pentagonUpgrade > points){ view.findViewById<Button>(R.id.pentaUpgrade).isEnabled = false }
                if(hexagonUpgrade > points){ view.findViewById<Button>(R.id.hexUpgrade).isEnabled = false }
                if(septagonUpgrade > points){ view.findViewById<Button>(R.id.septaUpgrade).isEnabled = false }
                if(octagonUpgrade > points){ view.findViewById<Button>(R.id.octoUpgrade).isEnabled = false }
            }
            window.isFocusable = true
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.showAtLocation(mainEnemy, Gravity.CENTER, 0, 0)
            window.update()
        }

        val triangleAnim = findViewById<ImageView>(R.id.imageView2)
        val squareAnim = findViewById<ImageView>(R.id.mainSquare)
        val pentagonAnim = findViewById<ImageView>(R.id.mainPentagon)
        val hexagonAnim = findViewById<ImageView>(R.id.mainHexagon)
        val septagonAnim = findViewById<ImageView>(R.id.mainSeptagon)
        val octagonAnim = findViewById<ImageView>(R.id.mainOctagon)
        triangleAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.heroanimation))
        squareAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.squareanimation))
        pentagonAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.pentagonanimation))
        hexagonAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.hexagonanimation))
        septagonAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.septagonanimation))
        octagonAnim.startAnimation(AnimationUtils.loadAnimation(this,R.anim.octagonanimation))


    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putDouble("enemyHealth", enemyHealth)
    }

    private fun calculateEnemyHealth(){
        points += enemySize[enemyIndex]
        when (enemyStage) {
            9 -> {
                enemyStage++
                enemyHealth = currentEnemyMax * 5
                tempEnemySave = currentEnemyMax
                points += enemyIndex * 10
            }
            10 -> {
                enemyIndex++
                enemyStage = 1
                enemyHealth = tempEnemySave * 1.5
                points += enemyIndex * 50
            }
            else -> {
                enemyStage++
                enemyHealth = currentEnemyMax * 1.5
                points += enemyIndex * 10
            }
        }
        currentStage.text = "Stage $enemyStage/10"
        enemyHealthBar.max = enemyHealth.roundToInt()
        enemyHealthBar.progress = enemyHealth.roundToInt()
        currentEnemyMax = enemyHealth
    }
    private fun determineSuffix(value:Double,index:Int): Int {
        var tempIndex = index
        var tempVal = value
        while (tempVal >= 1000) {
            tempVal /= 1000
            tempIndex++
        }
        if (tempIndex == 0){
            return tempIndex
        }
        while (tempVal < 1) {
            tempVal *= 1000
            tempIndex--
        }
        return tempIndex
    }

    private fun determineValue(value:Double,index: Int): Double {
        var tempIndex = index
        var tempVal = value
        while (tempVal >= 1000) {
            tempVal /= 1000

        }
        if (tempIndex == 0){
            return tempVal
        }
        while (tempVal < 1) {
            tempVal *= 1000
        }
        return tempVal
    }

    private fun setText(){
        var enemyMaxIndex = determineSuffix(currentEnemyMax,0)
        var enemyHealthIndex = determineSuffix(enemyHealth,0)
        val tempEnemyMax = determineValue(currentEnemyMax,enemyMaxIndex)
        val tempEnemyHealth = determineValue(enemyHealth,enemyHealthIndex)

        enemyHp.text = "HP: ${(tempEnemyHealth * 100.00).roundToInt() / 100.00}${bigNums[enemyHealthIndex]} / ${(tempEnemyMax * 100.00).roundToInt() / 100.00}${bigNums[enemyMaxIndex]}"
        pointsSuffix = determineSuffix(points,0)
        val tempPoints = determineValue(points,pointsSuffix)
        pointsText.text = "${if(points < 1000){tempPoints.roundToInt()} else{(tempPoints * 100.00).roundToInt() / 100.00}}${bigNums[pointsSuffix]}"
    }

}
