package controle;

import dao.ServiceLogin;
import meserreurs.MonException;
import domain.Utilisateur;
import utilitaires.MonMotPassHash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Controleur
 */
@WebServlet(name = "/ServletControleurLogin" ,urlPatterns={"/ServletControleurLogin"})
public class ControleurLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ACTION_TYPE = "action";
    private static final String LOGIN= "login";
    private static final String CONTROLELOGIN= "controleLogin";
    private static final String ERROR_KEY = "mesErreurs";
    private static final String ERROR_PAGE = "/erreur.jsp";

    private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        processusTraiteRequete(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        processusTraiteRequete(request, response);
    }

    protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);
        String destinationPage = ERROR_PAGE;

        if (LOGIN.equals(actionName)) {
            destinationPage = "/vues/formLogin.jsp";
        }
        else
            if ( CONTROLELOGIN.equals(actionName))
            {
                String login = request.getParameter("login");
                String pwd = request.getParameter("pwd");
                String message ="";
                try {

                    ServiceLogin unServiceLogin = new ServiceLogin();
                    Utilisateur unUtilisateur = unServiceLogin.getUtilistateur(login);
                    if (unUtilisateur != null) {
                        try {
                            // on récupère le sel
                            String sel = unUtilisateur.getSalt();
                            // on récupère le mot de passe
                            String mdp = unUtilisateur.getMotPasse();
                            // on génère le mot de passe avec les données de connexion
                            byte[] salt = MonMotPassHash.transformeEnBytes(unUtilisateur.getSalt());
                            char[] pwd_char = MonMotPassHash.converttoCharArray(pwd);
                            byte[] monpwdCo = MonMotPassHash.generatePasswordHash(pwd_char,salt);
                            // on récupère le mot de passe enregistré
                            byte[] mdp_byte = MonMotPassHash.transformeEnBytes(mdp);

                            if (MonMotPassHash.verifyPassword(monpwdCo,mdp_byte))
                            {
                                session = request.getSession();
                                session.setAttribute("id", unUtilisateur.getNumUtil());
                                destinationPage = "/index.jsp";
                            } else {
                                message = "mot de passe erroné";
                                request.setAttribute("message", message);
                                destinationPage = "/vues/formLogin.jsp";
                            }
                        } catch (Exception e) {
                            request.setAttribute("MesErreurs", e.getMessage());
                            destinationPage = "/vues/Erreur.jsp";
                        }
                    } else {
                        message = "login erroné";
                        request.setAttribute("message", message);
                        destinationPage = "/vues/formLogin.jsp";
                    }
                } catch (MonException e) {
                    request.setAttribute("MesErreurs", e.getMessage());
                    destinationPage = "/vues/Erreur.jsp";
                }
            }


        else {
            String messageErreur = "[" + actionName + "] n'est pas une action valide.";
            request.setAttribute(ERROR_KEY, messageErreur);
            destinationPage = "/vues/Erreur.jsp";
        }
        // Redirection vers la page jsp appropriee
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
        dispatcher.forward(request, response);

    }



}
