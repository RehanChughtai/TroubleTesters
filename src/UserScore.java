/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class UserScore 
{
    
    private String _username;
    private String _lastGameTimestamp;
    private int _classicScore = 0;
    private int _arcadeScore = 0;

    public UserScore(String username, String lastGameTimestamp, int classicScore, int arcadeScore)
    {
        _username = username;
        _lastGameTimestamp = lastGameTimestamp;
        _classicScore = classicScore;
        _arcadeScore = arcadeScore;
        
    }
    
    public UserScore()
    {
        
    }

    public int getScoreTotal() 
    {
        return _classicScore + _arcadeScore;
    }

    
    /**
     * Get the value of _arcadeScore
     *
     * @return the value of _arcadeScore
     */
    public int getArcadeScore() {
        return _arcadeScore;
    }

    /**
     * Set the value of _arcadeScore
     *
     * @param _arcadeScore new value of _arcadeScore
     */
    public void setArcadeScore(int arcadeScore) {
        this._arcadeScore = arcadeScore;
    }

    /**
     * Get the value of _classScore
     *
     * @return the value of _classScore
     */
    public int getClassicScore() {
        return _classicScore;
    }

    /**
     * Set the value of _classicScore
     *
     * @param _classicScore new value of _classicScore
     */
    public void setClassicScore(int classicScore) {
        this._classicScore = classicScore;
    }

    /**
     * Get the value of _lastGameTimestamp
     *
     * @return the value of _lastGameTimestamp
     */
    public String getLastGameTimestamp() {
        return _lastGameTimestamp;
    }

    /**
     * Set the value of _lastGameTimestamp
     *
     * @param _lastGameTimestamp new value of _lastGameTimestamp
     */
    public void setLastGameTimestamp(String lastGameTimestamp) {
        this._lastGameTimestamp = lastGameTimestamp;
    }

    
    /**
     * Get the value of _username
     *
     * @return the value of _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Set the value of _username
     *
     * @param _username new value of _username
     */
    public void setUsername(String username) {
        this._username = username;
    }

    
}
