
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class UserQuestionsDB 
{
    private String _lastError;
    private String _dbFilePath = "questions.csv";
    protected String _separator = ":";
    private Map<String, String> _questions = new TreeMap<String, String>();
    
    /**
     * Get the value of _lastError
     *
     * @return the value of _lastError
     */
    public String getLastError() 
    {
        return _lastError;
    }

    public UserQuestionsDB()
    {
    }
    public boolean Load()
    {
        boolean succeeded = true;

        BufferedReader reader = null;
        
        try
        {
            String line = "";
            
            reader = new BufferedReader(new FileReader(_dbFilePath));
            
            while ((line = reader.readLine()) != null) 
            {
                // ignore comments
                if (line.trim().startsWith("#")) continue;
                
                // ignore blank lines
                if (line.trim().length() == 0) continue;
                
                String[] tokens = line.split(_separator);
                
                if (tokens.length != 2) continue;
                
                String question = tokens[0].trim();
                String answer = tokens[1].trim();
                                
                if (!_questions.containsKey(question))
                {
                    _questions.put(question, answer);
                }
            }
        }
        catch(Exception ex)
        {
            succeeded = false;
            
            _lastError = ex.getMessage();
        }
        finally
        {
            if (null != reader) 
            {
                try 
                {
                    reader.close();
                } 
                catch (Exception e) 
                {
                    _lastError = e.getMessage();
                }
            }            
        }

        return succeeded;
    }
    public boolean Load(String dbFilePath)
    {
        _dbFilePath = dbFilePath;

        return Load();
    }
    public List<String> GetRandomQuestions(int count)
    {
        int numberOfQuestionsWanted = count;
        
        if (0 == count)
        {
            numberOfQuestionsWanted = _questions.size(); // get all the questions
        }
        
        List<String> questions = new ArrayList<String>(numberOfQuestionsWanted);
        
        Object[] keys = _questions.keySet().toArray();
        
        while(questions.size() < numberOfQuestionsWanted)
        {
            Random rand = new Random();

            int randomIndex = rand.nextInt(_questions.size());

            String key = (String)keys[randomIndex];

            if (!questions.contains(key))
            {
                questions.add(key);
            }
        }

        return questions;
    }
    public String GetAnswer(String question)
    {
        return _questions.get(question);
    }
}
